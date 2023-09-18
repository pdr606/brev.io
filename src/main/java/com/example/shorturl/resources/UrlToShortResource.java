package com.example.shorturl.resources;


import com.example.shorturl.dto.UrlCustomizeData;
import com.example.shorturl.dto.UrlDTO;
import com.example.shorturl.dto.UrlResonseDTO;
import com.example.shorturl.dto.UrlStatsResponseDTO;
import com.example.shorturl.entities.UrlToShort;
import com.example.shorturl.services.UrlToShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/url")
public class UrlToShortResource {


    @Value("${app.base.url}")
    private String baseUrl;
    @Autowired
    private UrlToShortService urlToShortService;

    @PostMapping
    public ResponseEntity<UrlResonseDTO> generateShortUrl(@RequestBody UrlDTO data){
        UrlToShort objUrlShort = new UrlToShort(null, data.url());
        urlToShortService.createShortUrl(objUrlShort);
        UrlResonseDTO urlResponse = new UrlResonseDTO(baseUrl + objUrlShort.getCodeOfUrl());
        return ResponseEntity.ok().body(urlResponse);
    }

    @PostMapping(value = "/customize")
    public ResponseEntity<UrlResonseDTO> generateCustomizeUrl(@RequestBody UrlCustomizeData data){
        urlToShortService.checkIfCodeExist(data.code());
        UrlToShort objUrlShort = new UrlToShort(null, data.url(), data.code());
        UrlResonseDTO urlResponse = new UrlResonseDTO(baseUrl + objUrlShort.getCodeOfUrl());
        urlToShortService.saveCustomizeShortUrl(objUrlShort);
        return ResponseEntity.ok().body(urlResponse);
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<UrlStatsResponseDTO> searchShortUrl(@PathVariable String code){
        UrlToShort objUrlShort = urlToShortService.findUrlByCode(code);
        return ResponseEntity.ok().body(new UrlStatsResponseDTO(objUrlShort.getOriginalUrl(), objUrlShort.getNumberOfAcess(), objUrlShort.getDateOfLastAcess()));
    }
}
