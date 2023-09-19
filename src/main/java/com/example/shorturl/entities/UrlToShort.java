package com.example.shorturl.entities;


import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.*;


@Entity
@Table(name = "short_url")
public class UrlToShort {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String originalUrl;
    private String codeOfUrl;

    private Integer numberOfAcess;


    @Column(name = "dataOfLastAcess", columnDefinition = "TIMESTAMP")
    private ZonedDateTime dateOfLastAcess;

    public UrlToShort(){
        this.codeOfUrl = generateCode();
    }
    public UrlToShort(Long id, String originalUrl) {
        Id = id;
        this.originalUrl = originalUrl;
        this.codeOfUrl = generateCode();
        this.numberOfAcess = 0;
    }

    public UrlToShort(Long id, String originalUrl, String codeOfUrl) {
        Id = id;
        this.originalUrl = originalUrl;
        this.codeOfUrl = codeOfUrl;
        this.numberOfAcess = 0;
    }

    public Integer getNumberOfAcess() {
        return numberOfAcess;
    }

    public void setNumberOfAcess(Integer numberOfAcess) {
        this.numberOfAcess = numberOfAcess;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getCodeOfUrl() {
        return codeOfUrl;
    }

    public void setCodeOfUrl(String codeOfUrl) {
        this.codeOfUrl = codeOfUrl;
    }

    public ZonedDateTime getDateOfLastAcess() {
        return dateOfLastAcess;
    }

    public void setDateOfLastAcess(ZonedDateTime dateOfLastAcess) {
        this.dateOfLastAcess = dateOfLastAcess;
    }

    public void updateAcess(){
        setNumberOfAcess(getNumberOfAcess() + 1);
    }

    public void updateDateOfLastAcess() {
        setDateOfLastAcess(ZonedDateTime.ofInstant(LocalDateTime.now(), ZoneOffset.UTC, ZoneId.of("America/Sao_Paulo")));
    }

    public String generateCode(){
        String permitedCaracter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#!$%";

        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for(int i = 0; i < 5; i++){
            int indice = random.nextInt(permitedCaracter.length());
            char caracter =permitedCaracter.charAt(indice);
            code.append(caracter);
        }

        return code.toString();
    }
}
