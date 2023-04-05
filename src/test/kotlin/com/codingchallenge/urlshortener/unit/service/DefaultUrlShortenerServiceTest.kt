package com.codingchallenge.urlshortener.unit.service

import com.codingchallenge.urlshortener.controller.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.entity.Url
import com.codingchallenge.urlshortener.repository.UrlRepository
import com.codingchallenge.urlshortener.service.DefaultUrlShortenerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.EntityNotFoundException

/**
 * Unit Tests for DefaultUrlShortenerService
 *
 * @author Aidar Aibekov
 */
@SpringBootTest
class DefaultUrlShortenerServiceTest {

    @InjectMocks
    private lateinit var defaultUrlShortenerService: DefaultUrlShortenerService

    @Mock
    private lateinit var urlRepository: UrlRepository

    @Test
    fun create_withValidDto_shouldReturnReadShortUrlDto() {
        val createShortUrldto = CreateShortUrlDto(url = "some long url")

        val url = Url(
            id = 1L,
            originalUrl = createShortUrldto.url,
            urlKey = "someGeneratedUrlKey",
            createdAt = ZonedDateTime.now()
        )

        `when`(urlRepository.findByOriginalUrl(createShortUrldto.url)).thenReturn(null)
        `when`(urlRepository.save(any())).thenReturn(url)

        val result = defaultUrlShortenerService.create(createShortUrldto)
        verify(urlRepository, times(1)).findByOriginalUrl(createShortUrldto.url)
        verify(urlRepository, times(1)).save(any())
        assertNotNull(result)
        assertNotNull(result.urlKey)
        assertEquals(result.urlKey, url.urlKey)
    }

    @Test
    fun create_withValidDtoWithExistingUrl_shouldReturnReadShortUrlDto() {
        val createShortUrlDto = CreateShortUrlDto(url = "some long url")

        val url = Url(
            id = 1L,
            originalUrl = createShortUrlDto.url,
            urlKey = UUID.randomUUID().toString(),
            createdAt = ZonedDateTime.now()
        )

        `when`(urlRepository.findByOriginalUrl(createShortUrlDto.url)).thenReturn(url)

        val result = defaultUrlShortenerService.create(createShortUrlDto)
        verify(urlRepository, times(1)).findByOriginalUrl(createShortUrlDto.url)
        assertNotNull(result)
        assertNotNull(result.urlKey)
        assertEquals(result.urlKey, url.urlKey)
    }

    @Test
    fun getById_withValidUrlKey_shouldReturnReadUrlDto() {
        val url = Url(
            id = 1L,
            originalUrl = "original url",
            urlKey = "urlKey",
            createdAt = ZonedDateTime.now()
        )

        `when`(urlRepository.findById(1L)).thenReturn(Optional.of(url))

        val result = defaultUrlShortenerService.getById(1L)
        verify(urlRepository, times(1)).findById(1L)
        assertNotNull(result)
        assertNotNull(result.originalUrl)
        assertEquals(result.originalUrl, url.originalUrl)
    }

    @Test
    fun getById_withId_shouldThrowENFE() {
        `when`(urlRepository.findById(777L)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { defaultUrlShortenerService.getById(777L) }
    }

    @Test
    fun getByUrlKey_withValidUrlKey_shouldReturnReadUrlDto() {
        val urlKey = UUID.randomUUID().toString()

        val url = Url(
            id = 1L,
            originalUrl = "original url",
            urlKey = urlKey,
            createdAt = ZonedDateTime.now()
        )

        `when`(urlRepository.findByUrlKey(urlKey)).thenReturn(url)

        val result = defaultUrlShortenerService.getByUrlKey(urlKey)
        verify(urlRepository, times(1)).findByUrlKey(urlKey)
        assertNotNull(result)
        assertNotNull(result.originalUrl)
        assertEquals(result.originalUrl, url.originalUrl)
    }

    @Test
    fun getByUrlKey_withNotValidUrlKey_shouldThrowENFE() {
        val urlKey = "not existing url key"

        `when`(urlRepository.findByUrlKey(urlKey)).thenReturn(null)

        assertThrows<EntityNotFoundException> { defaultUrlShortenerService.getByUrlKey(urlKey) }
    }
}