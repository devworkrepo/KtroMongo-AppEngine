package com.example.config

import io.ktor.http.*
import io.ktor.server.plugins.cors.*


object HttpConfig {
    operator fun invoke(config: CORSConfig) {
          config.apply {
              allowMethod(HttpMethod.Post)
              allowMethod(HttpMethod.Get)
              allowHeader(HttpHeaders.ContentType)
              allowHeader(HttpHeaders.Allow)
              allowHeader(HttpHeaders.Accept)
              allowHeader(HttpHeaders.AcceptLanguage)
              allowHeader(HttpHeaders.Authorization)
              allowHeader(HttpHeaders.XRequestId)
              anyHost()
              allowNonSimpleContentTypes = true
              allowCredentials = true
              allowSameOrigin = true

          }
    }
}
