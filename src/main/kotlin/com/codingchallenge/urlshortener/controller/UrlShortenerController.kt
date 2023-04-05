package com.codingchallenge.urlshortener.controller

import com.codingchallenge.urlshortener.controller.dto.CreateShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadShortUrlDto
import com.codingchallenge.urlshortener.controller.dto.ReadUrlDto
import com.codingchallenge.urlshortener.service.UrlShortenerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * UrlShortenerController is RestController that serves as entry point to Application
 *
 * @author Aidar Aibekov
 */
@RestController
@RequestMapping("/api/v1/urls")
class UrlShortenerController(val urlShortenerService: UrlShortenerService) {

    @PostMapping
    @ResponseBody
    fun create(@Valid @RequestBody dto: CreateShortUrlDto): ReadShortUrlDto = urlShortenerService.create(dto)

    @GetMapping("/{id}")
    @ResponseBody
    fun getById(@PathVariable id: Long): ReadUrlDto = urlShortenerService.getById(id)

    @GetMapping("/urlKey/{urlKey}")
    @ResponseBody
    fun getByUrlKey(@PathVariable urlKey: String): ReadUrlDto = urlShortenerService.getByUrlKey(urlKey)

}