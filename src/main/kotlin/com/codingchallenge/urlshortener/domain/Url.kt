package com.codingchallenge.urlshortener.domain

import java.time.ZonedDateTime
import javax.persistence.*

/**
 * Url Entity
 *
 * @author Aidar Aibekov
 */
@Entity
@Table(name = "urls")
class Url(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "original_url")
    val originalUrl: String,

    @Column(name = "url_key")
    val urlKey: String,

    @Column(name = "created_at")
    val createdAt: ZonedDateTime

)