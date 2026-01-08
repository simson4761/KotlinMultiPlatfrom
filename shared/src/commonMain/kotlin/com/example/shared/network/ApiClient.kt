package com.example.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object ApiClient {
    // Add logger parameter and provide a default value
    private val defaultLogger = object : Logger {
        override fun log(message: String) {
            println("Ktor Client: $message")
        }
    }

    fun createApiClient(
        networkConfig: NetworkConfig
    ) : HttpClient{
        return HttpClient {
            if(networkConfig.debugMode){
                install(Logging) {
                    logger = defaultLogger
                    level = networkConfig.logLevel
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = networkConfig.requestTimeoutMillis
                connectTimeoutMillis = networkConfig.connectTimeoutMillis
                socketTimeoutMillis = networkConfig.socketTimeoutMillis
            }

            install(DefaultRequest){
                url(networkConfig.baseUrl) // base url
            }

            expectSuccess = true

            HttpResponseValidator {

            }
        }
    }
}