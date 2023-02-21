package com.codingchallenge.urlshortener.repository

import com.codingchallenge.urlshortener.domain.entity.Url
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository : JpaRepository<Url, Long> {
    fun findByUrlKey(urlKey: String): Url?
}