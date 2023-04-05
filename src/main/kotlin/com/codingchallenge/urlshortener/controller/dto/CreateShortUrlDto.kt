package com.codingchallenge.urlshortener.controller.dto

import javax.validation.constraints.NotBlank

class CreateShortUrlDto(
    @NotBlank(message = "URL is required")
    val url: String
)