package com.codingchallenge.urlshortener.service

import com.codingchallenge.urlshortener.domain.Url
import com.codingchallenge.urlshortener.domain.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadOriginalUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.repository.UrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime
import java.util.UUID

/**
 * UrlShortenerService is interface for one or more implementations of feature
 *
 * @author Aidar Aibekov
 */
interface UrlShortenerService {
    fun shorten(createShortUrlDto: CreateShortUrlDto): ReadShortUrlDto
    fun getOriginalUrl(urlKey: String): ReadOriginalUrlDto
}

/**
 * DefaultUrlShortenerService is default implementaiton of feature
 *
 * @author Aidar Aibekov
 */
@Service
class DefaultUrlShortenerService(val urlRepository: UrlRepository) : UrlShortenerService {
    @Transactional
    override fun shorten(createShortUrlDto: CreateShortUrlDto): ReadShortUrlDto {
        val url = Url(
            originalUrl = createShortUrlDto.url,
            urlKey = UUID.randomUUID().toString(),
            createdAt = ZonedDateTime.now()
        )

        val savedUrl = urlRepository.save(url)

        return ReadShortUrlDto(urlKey = savedUrl.urlKey)
    }

    override fun getOriginalUrl(urlKey: String): ReadOriginalUrlDto {
        TODO("Not yet implemented")
    }

}