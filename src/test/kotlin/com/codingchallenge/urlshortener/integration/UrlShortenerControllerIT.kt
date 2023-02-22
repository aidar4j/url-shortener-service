package com.codingchallenge.urlshortener.integration

import com.codingchallenge.urlshortener.UrlshortenerApplication
import com.codingchallenge.urlshortener.domain.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadOriginalUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadShortUrlDto
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
import java.util.*

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
    fun getOriginalUrl_withValidUrlKey_shouldReturnResponseWithOriginalKey() {
        val urlKey = UUID.randomUUID().toString()

        `when`(urlShortenerService.getOriginalUrl(urlKey)).thenReturn(ReadOriginalUrlDto(originalUrl = "original url"))

        val result = restTemplate.getForEntity("/api/v1/original_url/$urlKey", ReadOriginalUrlDto::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }

    @Test
    fun shortenUrl_withValidCreateShortUrlDto_shouldReturnResponseWithOriginalKey() {
        val createShortUrlDto = CreateShortUrlDto(
            url = "looooong url"
        )

        `when`(urlShortenerService.shortenUrl(createShortUrlDto)).thenReturn(ReadShortUrlDto(urlKey = "uuid"))

        val result = restTemplate.postForEntity("/api/v1/shorten", createShortUrlDto, ReadShortUrlDto::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }
}