package com.example.shorturl.dto;

import java.time.ZonedDateTime;

public record UrlStatsResponseDTO(String originalUrl, Integer numberOfAcess, ZonedDateTime dateOfLastAcess) {
}
