package com.codingchallenge.urlshortener.repository

import com.codingchallenge.urlshortener.domain.Url
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository : JpaRepository<Url, Long>