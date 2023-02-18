package com.codingchallenge.urlshortener.domain

import javax.persistence.Entity

/**
 * Url Entity
 *
 * @author Aidar Aibekov
 */
@Entity
class Url(
    val id: Long,
    val originalUrl: String,
    val urlKey: String
)