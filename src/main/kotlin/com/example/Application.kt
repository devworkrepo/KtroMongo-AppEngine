package com.example

import com.example.config.*
import com.example.route.configureRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit = EngineMain.main(args)


fun Application.module() {

    install(Koin){InjectionConfig(this)}
    install(ContentNegotiation) {ContentConfig(this)}
    install(DoubleReceive)
    install(RequestValidation){ValidationConfig(this)}
    install(CallLogging){LoggingConfig(this)}
    install(CORS){HttpConfig(this)}
    install(StatusPages){ExceptionConfig(this)}
    install(Routing)
    authentication { jwt { AuthConfig(this) } }
    configureRouting()
}


