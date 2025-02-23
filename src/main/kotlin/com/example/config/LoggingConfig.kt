package com.example.config

import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.*


object LoggingConfig{
    operator fun invoke(config : CallLoggingConfig){
        config.apply {
            level = Level.INFO
            filter { call -> call.request.path().startsWith("/") }
        }
    }
}
