package com.codingchallenge.urlshortener.unit.controller

import com.codingchallenge.urlshortener.controller.UrlShortenerController
import com.codingchallenge.urlshortener.controller.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadUrlDto
import com.codingchallenge.urlshortener.service.DefaultUrlShortenerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.time.ZonedDateTime
import java.util.*

/**
 * Unit Tests for UrlShortenerController
 *
 * @author Aidar Aibekov
 */
@SpringBootTest
class UrlShortenerControllerTest {

    @InjectMocks
    private lateinit var urlShortenerController: UrlShortenerController

    @Mock
    private lateinit var defaultUrlShortenerService: DefaultUrlShortenerService

    @Test
    fun create_withValidDto_shouldReturnReadShortUrlDto() {
        val createShortUrlDto = CreateShortUrlDto(url = "some long url")

        val readShortUrlDto = ReadShortUrlDto(
            urlKey = "someGeneratedUrlKey"
        )

        `when`(defaultUrlShortenerService.create(createShortUrlDto)).thenReturn(readShortUrlDto)

        val result = urlShortenerController.create(createShortUrlDto)
        verify(defaultUrlShortenerService, times(1)).create(createShortUrlDto)
        assertNotNull(result)
        assertNotNull(result.urlKey)
        assertEquals(result.urlKey, readShortUrlDto.urlKey)
    }

    @Test
    fun getById_withValidUrlKey_shouldReturnReadUrlDto() {
        val id = 1L
        val readUrlDto = ReadUrlDto(
            id = id,
            originalUrl = "original url",
            urlKey = "url key",
            createdAt = ZonedDateTime.now()
        )

        `when`(defaultUrlShortenerService.getById(id)).thenReturn(readUrlDto)

        val result = urlShortenerController.getById(id)
        verify(defaultUrlShortenerService, times(1)).getById(id)
        assertNotNull(result)
        assertNotNull(result.originalUrl)
        assertEquals(result.originalUrl, readUrlDto.originalUrl)
    }

    @Test
    fun getByUrlKey_withValidUrlKey_shouldReturnReadUrlDto() {
        val urlKey = UUID.randomUUID().toString()
        val readUrlDto = ReadUrlDto(
            id = 1L,
            originalUrl = "original url",
            urlKey = urlKey,
            createdAt = ZonedDateTime.now()
        )

        `when`(defaultUrlShortenerService.getByUrlKey(urlKey)).thenReturn(readUrlDto)

        val result = urlShortenerController.getByUrlKey(urlKey)
        verify(defaultUrlShortenerService, times(1)).getByUrlKey(urlKey)
        assertNotNull(result)
        assertNotNull(result.originalUrl)

    }
}