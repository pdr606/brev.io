package com.example.shorturl.services;


import com.example.shorturl.entities.UrlToShort;
import com.example.shorturl.repositories.UrlToShortRepository;
import com.example.shorturl.services.exceptions.ConflictException;
import com.example.shorturl.services.exceptions.DatabaseException;
import com.example.shorturl.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class UrlToShortService {

    @Autowired
    public UrlToShortRepository urlToShortRepository;

    public UrlToShort createShortUrl(UrlToShort urlToShort) {
        try {
            while (urlToShortRepository.findExistCode(urlToShort.getCodeOfUrl()) != null) {
                urlToShort.generateCode();
            }
            return urlToShortRepository.save(urlToShort);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public UrlToShort saveCustomizeShortUrl(UrlToShort urlToShort){
       return urlToShortRepository.save(urlToShort);
    }
    public void updateAcess(UrlToShort urlToShort) {
        if (urlToShortRepository.findExistCode(urlToShort.getCodeOfUrl()) != null) {
            urlToShort.updateAcess();
            urlToShort.updateDateOfLastAcess();
            urlToShortRepository.save(urlToShort);
        }
    }

    public UrlToShort findUrlByCode(String code){
        UrlToShort url = urlToShortRepository.findExistCode(code);
        if(url == null){
            throw new ResourceNotFoundException(code);
        }
        return url;
    }

    public void saveUrl(UrlToShort urlToShort){
        urlToShortRepository.save(urlToShort);
    }

    public UrlToShort checkIfCodeExist(String code){
        UrlToShort url = urlToShortRepository.findExistCode(code);
        if(url != null){
            throw new ConflictException(code);
        }
        return url;
    }

}
