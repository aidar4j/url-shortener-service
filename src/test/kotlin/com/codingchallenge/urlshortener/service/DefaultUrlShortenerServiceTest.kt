package com.codingchallenge.urlshortener.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DefaultUrlShortenerServiceTest {

    @Autowired
    private lateinit var defaultUrlShortenerService: DefaultUrlShortenerService

    @Test
    fun shortenUrl_withValidDto_shouldReturnReadShortUrlDto() {
        // implement tests
    }
}