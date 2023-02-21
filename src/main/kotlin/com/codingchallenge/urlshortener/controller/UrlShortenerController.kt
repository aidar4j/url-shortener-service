package com.codingchallenge.urlshortener.controller

import com.codingchallenge.urlshortener.domain.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadOriginalUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.service.UrlShortenerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * UrlShortenerController is RestController that serves as entry point to Application
 *
 * @author Aidar Aibekov
 */
@RestController
@RequestMapping("/api/v1")
class UrlShortenerController(val urlShortenerService: UrlShortenerService) {

    @PostMapping("/shorten")
    @ResponseBody
    fun shortenUrl(@RequestBody @Valid dto: CreateShortUrlDto): ReadShortUrlDto = urlShortenerService.shortenUrl(dto)

    @GetMapping("/original_url/{urlKey}")
    @ResponseBody
    fun getOriginalUrl(@PathVariable urlKey: String): ReadOriginalUrlDto = urlShortenerService.getOriginalUrl(urlKey)

}