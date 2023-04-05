package com.codingchallenge.urlshortener.service

import com.codingchallenge.urlshortener.controller.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadUrlDto
import com.codingchallenge.urlshortener.domain.entity.Url
import com.codingchallenge.urlshortener.repository.UrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime
import javax.persistence.EntityNotFoundException

/**
 * UrlShortenerService is interface for one or more implementations of feature
 *
 * @author Aidar Aibekov
 */
interface UrlShortenerService {
    fun create(createShortUrlDto: CreateShortUrlDto): ReadShortUrlDto
    fun getById(id: Long): ReadUrlDto
    fun getByUrlKey(urlKey: String): ReadUrlDto
}

/**
 * DefaultUrlShortenerService is default implementaiton of feature
 *
 * @author Aidar Aibekov
 */
@Service
class DefaultUrlShortenerService(val urlRepository: UrlRepository) : UrlShortenerService {

    @Transactional
    override fun create(createShortUrlDto: CreateShortUrlDto): ReadShortUrlDto {
        val existingUrlEntity: Url? = urlRepository.findByOriginalUrl(createShortUrlDto.url)

        val savedUrlEntity: Url = if (existingUrlEntity == null) {
            val url = Url(
                originalUrl = createShortUrlDto.url,
                urlKey = generateUrlKey(),
                createdAt = ZonedDateTime.now()
            )

            urlRepository.save(url)
        } else {
            existingUrlEntity
        }

        return ReadShortUrlDto(urlKey = savedUrlEntity.urlKey)
    }

    @Transactional(readOnly = true)
    override fun getById(id: Long): ReadUrlDto {
        val url = urlRepository.findById(id)
            .orElseThrow { EntityNotFoundException("URL Entity with id $id is not found!") }

        return ReadUrlDto(
            id = url.id!!,
            originalUrl = url.originalUrl,
            urlKey = url.urlKey,
            createdAt = url.createdAt
        )
    }

    @Transactional(readOnly = true)
    override fun getByUrlKey(urlKey: String): ReadUrlDto {
        val url = urlRepository.findByUrlKey(urlKey)
            ?: throw EntityNotFoundException("URL Entity with urlKey $urlKey is not found!")

        return ReadUrlDto(
            id = url.id!!,
            originalUrl = url.originalUrl,
            urlKey = url.urlKey,
            createdAt = url.createdAt
        )
    }

    private fun generateUrlKey(): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')

        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }

}