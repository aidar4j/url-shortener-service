package com.codingchallenge.urlshortener.domain.dto

import javax.validation.constraints.NotBlank

class CreateShortUrlDto(
    @field:NotBlank(message = "URL is required")
    val url: String
)