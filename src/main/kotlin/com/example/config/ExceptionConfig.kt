package com.example.config

import com.example.data.repository.AppRepository
import com.example.model.respond.ErrorLogRespond
import com.example.util.ApiResponse
import com.example.util.loggedInUserId
import com.example.util.withTry
import com.mongodb.MongoWriteException
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject


object ExceptionConfig {

    operator fun invoke(config: StatusPagesConfig) {
        config.exception<Throwable> { call, cause ->
            var errorLogRespond = when (cause) {
                is MongoWriteException -> {
                    val details = cause.message.toString()

                    ErrorLogRespond(
                        errorMessage = details,
                        stackTrace = cause.stackTraceToString()
                    )
                }

                else -> {
                    ErrorLogRespond(
                        errorMessage = cause.message.toString(),
                        stackTrace = cause.stackTraceToString()
                    )
                }
            }



            call.apply {
                val repository by inject<AppRepository>()
                withTry(false) {
                    errorLogRespond = errorLogRespond.copy(
                        route = call.request.uri,
                        userId = call.loggedInUserId()
                    )
                    repository.saveExceptionLog(errorLogRespond)
                }
                respond(ApiResponse.exception(data = errorLogRespond))
            }

        }
    }

}