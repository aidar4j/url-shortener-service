package com.codingchallenge.urlshortener.integration

import com.codingchallenge.urlshortener.UrlshortenerApplication
import com.codingchallenge.urlshortener.controller.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadUrlDto
import com.codingchallenge.urlshortener.service.UrlShortenerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import java.time.ZonedDateTime
import java.util.*

/**
 * Integration Tests for UrlShortenerController
 *
 * @author Aidar Aibekov
 */
@SpringBootTest(
    classes = [UrlshortenerApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class UrlShortenerControllerIT {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @MockBean
    private lateinit var urlShortenerService: UrlShortenerService

    @Test
    fun getById_withValidUrlKey_shouldReturnResponseWithOriginalKey() {
        val id = 1L
        val readUrlDto = ReadUrlDto(
            id = id,
            originalUrl = "original url",
            urlKey = "url key",
            createdAt = ZonedDateTime.now()
        )

        `when`(urlShortenerService.getById(id)).thenReturn(readUrlDto)

        val result = restTemplate.getForEntity("/api/v1/urls/$id", ReadUrlDto::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }

    @Test
    fun getByUrlKey_withValidUrlKey_shouldReturnResponseWithOriginalKey() {
        val urlKey = UUID.randomUUID().toString()
        val readUrlDto = ReadUrlDto(
            id = 1L,
            originalUrl = "original url",
            urlKey = urlKey,
            createdAt = ZonedDateTime.now()
        )

        `when`(urlShortenerService.getByUrlKey(urlKey)).thenReturn(readUrlDto)

        val result = restTemplate.getForEntity("/api/v1/urls/urlKey/$urlKey", ReadUrlDto::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }

    @Test
    fun create_withValidCreateShortUrlDto_shouldReturnResponseWithOriginalKey() {
        val createShortUrlDto = CreateShortUrlDto(
            url = "looooong url"
        )

        `when`(urlShortenerService.create(createShortUrlDto)).thenReturn(ReadShortUrlDto(urlKey = "uuid"))

        val result = restTemplate.postForEntity("/api/v1/urls", createShortUrlDto, ReadShortUrlDto::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }
}