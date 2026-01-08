package com.example.shared.network

import io.ktor.client.plugins.logging.LogLevel
import io.ktor.http.ContentType

data class NetworkConfig(
    val baseUrl:String = "http://tvam.co.in",
    val connectTimeoutMillis: Long = 15000L,
    val requestTimeoutMillis: Long = 15000L,
    val socketTimeoutMillis: Long = 15000L,
    val logLevel: LogLevel = LogLevel.ALL,
    val contentType: ContentType = ContentType.Application.Json,
    val debugMode : Boolean = true
)
