package com.codingchallenge.urlshortener.unit.controller

import com.codingchallenge.urlshortener.controller.UrlShortenerController
import com.codingchallenge.urlshortener.domain.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadOriginalUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.service.DefaultUrlShortenerService
import org.junit.jupiter.api.Assertions
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
            urlKey = UUID.randomUUID().toString()
        )

        `when`(defaultUrlShortenerService.shortenUrl(createShortUrlDto)).thenReturn(readShortUrlDto)

        val result = urlShortenerController.shortenUrl(createShortUrlDto)
        verify(defaultUrlShortenerService, times(1)).shortenUrl(createShortUrlDto)
        Assertions.assertNotNull(result)
        Assertions.assertNotNull(result.urlKey)
    }

    @Test
    fun getOriginalUrl_withValidUrlKey_shouldReturnReadOriginalUrlDto() {
        val urlKey = UUID.randomUUID().toString()

        val readOriginalUrlDto = ReadOriginalUrlDto(originalUrl = "original url")

        `when`(defaultUrlShortenerService.getOriginalUrl(urlKey)).thenReturn(readOriginalUrlDto)

        val result = urlShortenerController.getOriginalUrl(urlKey)
        verify(defaultUrlShortenerService, times(1)).getOriginalUrl(urlKey)
        Assertions.assertNotNull(result)
        Assertions.assertNotNull(result.originalUrl)
        Assertions.assertEquals(result.originalUrl, readOriginalUrlDto.originalUrl)
    }
}