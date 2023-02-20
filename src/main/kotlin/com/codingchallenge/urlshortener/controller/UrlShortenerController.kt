package com.codingchallenge.urlshortener.controller

import com.codingchallenge.urlshortener.domain.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.domain.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.service.UrlShortenerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * UrlShortenerController is RestController that serves as entry point to Application
 *
 * @author Aidar Aibekov
 */
@RestController
@RequestMapping("/api/v1")
class UrlShortenerController(val urlShortenerService: UrlShortenerService) {

    @PostMapping("/shorten")
    fun createExample(@RequestBody dto: CreateShortUrlDto): ReadShortUrlDto = urlShortenerService.shorten(dto)

}