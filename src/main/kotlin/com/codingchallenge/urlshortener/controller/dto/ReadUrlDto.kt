package com.codingchallenge.urlshortener.controller.dto

import java.time.ZonedDateTime

class ReadUrlDto(
    val id: Long,
    val originalUrl: String,
    val urlKey: String,
    val createdAt: ZonedDateTime
)