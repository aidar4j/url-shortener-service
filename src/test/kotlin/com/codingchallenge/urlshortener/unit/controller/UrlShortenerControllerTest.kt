package com.codingchallenge.urlshortener.unit.controller

import com.codingchallenge.urlshortener.controller.UrlShortenerController
import com.codingchallenge.urlshortener.domain.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadOriginalUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.service.DefaultUrlShortenerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class UrlShortenerControllerTest {

    @InjectMocks
    private lateinit var urlShortenerController: UrlShortenerController

    @Mock
    private lateinit var defaultUrlShortenerService: DefaultUrlShortenerService

    @Test
    fun shortenUrl_withValidDto_shouldReturnReadShortUrlDto() {
        val createShortUrlDto = CreateShortUrlDto(url = "some long url")

        val readShortUrlDto = ReadShortUrlDto(
            urlKey = "someGeneratedUrlKey"
        )

        `when`(defaultUrlShortenerService.shortenUrl(createShortUrlDto)).thenReturn(readShortUrlDto)

        val result = urlShortenerController.shortenUrl(createShortUrlDto)
        verify(defaultUrlShortenerService, times(1)).shortenUrl(createShortUrlDto)
        assertNotNull(result)
        assertNotNull(result.urlKey)
        assertEquals(result.urlKey, readShortUrlDto.urlKey)
    }

    @Test
    fun getOriginalUrl_withValidUrlKey_shouldReturnReadOriginalUrlDto() {
        val urlKey = UUID.randomUUID().toString()

        val readOriginalUrlDto = ReadOriginalUrlDto(originalUrl = "original url")

        `when`(defaultUrlShortenerService.getOriginalUrl(urlKey)).thenReturn(readOriginalUrlDto)

        val result = urlShortenerController.getOriginalUrl(urlKey)
        verify(defaultUrlShortenerService, times(1)).getOriginalUrl(urlKey)
        assertNotNull(result)
        assertNotNull(result.originalUrl)
        assertEquals(result.originalUrl, readOriginalUrlDto.originalUrl)
    }
}