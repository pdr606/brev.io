package com.example.shorturl.repositories;

import com.example.shorturl.entities.UrlToShort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlToShortRepository extends JpaRepository<UrlToShort, Long> {

    @Query("SELECT u FROM UrlToShort u WHERE u.codeOfUrl = :code")
    UrlToShort findExistCode(String code);


}
