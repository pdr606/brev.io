package com.example.shorturl.resources;


import com.example.shorturl.entities.UrlToShort;
import com.example.shorturl.services.UrlToShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class DefaultResource {

    @Autowired
    private UrlToShortService urlToShortService;


    @GetMapping(value = "/{code}")
    public RedirectView acessUrl(@PathVariable String code ){
        UrlToShort urlToShort = urlToShortService.findUrlByCode(code);
        if(urlToShort != null){
            urlToShortService.updateAcess(urlToShort);
            return new RedirectView(urlToShort.getOriginalUrl());
        }
        return null;
    }
}
