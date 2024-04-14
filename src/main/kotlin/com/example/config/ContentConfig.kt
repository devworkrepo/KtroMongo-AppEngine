package com.example.config

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object ContentConfig {
    @OptIn(ExperimentalSerializationApi::class)
    operator fun invoke(config: ContentNegotiationConfig) = config.apply{
        json(
            Json {
                encodeDefaults = false
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }
}