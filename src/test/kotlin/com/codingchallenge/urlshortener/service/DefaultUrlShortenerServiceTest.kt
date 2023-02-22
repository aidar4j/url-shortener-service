package com.codingchallenge.urlshortener.service

import com.codingchallenge.urlshortener.domain.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.entity.Url
import com.codingchallenge.urlshortener.repository.UrlRepository
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


@SpringBootTest
class DefaultUrlShortenerServiceTest {

    @InjectMocks
    private lateinit var defaultUrlShortenerService: DefaultUrlShortenerService

    @Mock
    private lateinit var urlRepository: UrlRepository

    @Test
    fun shortenUrl_withValidDto_shouldReturnReadShortUrlDto() {
        val createShortUrldto = CreateShortUrlDto(url = "some long url")

        val url = Url(
            id = 1L,
            originalUrl = createShortUrldto.url,
            urlKey = UUID.randomUUID().toString(),
            createdAt = ZonedDateTime.now()
        )

        `when`(urlRepository.save(any())).thenReturn(url)

        val result = defaultUrlShortenerService.shortenUrl(createShortUrldto)
        verify(urlRepository, times(1)).save(any())
        assertNotNull(result)
        assertNotNull(result.urlKey)
    }

    @Test
    fun getOriginalUrl_withValidUrlKey_shouldReturnReadOriginalUrlDto() {
        val urlKey = UUID.randomUUID().toString()

        val url = Url(
            id = 1L,
            originalUrl = "original url",
            urlKey = urlKey,
            createdAt = ZonedDateTime.now()
        )

        `when`(urlRepository.findByUrlKey(urlKey)).thenReturn(url)

        val result = defaultUrlShortenerService.getOriginalUrl(urlKey)
        verify(urlRepository, times(1)).findByUrlKey(urlKey)
        assertNotNull(result)
        assertNotNull(result.originalUrl)
        assertEquals(result.originalUrl, url.originalUrl)
    }

    @Test
    fun getOriginalUrl_withNotValidUrlKey_shouldThrowENFE() {
        val urlKey = "not existing url key"

        `when`(urlRepository.findByUrlKey(urlKey)).thenReturn(null)

        assertThrows<EntityNotFoundException> { defaultUrlShortenerService.getOriginalUrl(urlKey) }
    }
}