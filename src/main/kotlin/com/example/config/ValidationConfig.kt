package com.example.config

import com.example.model.request.LoginRequest
import com.example.model.request.UserCreateRequest
import com.example.model.request.scheme.SchemeCreateRequest
import com.example.validation.AdminValidation
import com.example.validation.AuthValidation
import io.ktor.server.plugins.requestvalidation.*

object ValidationConfig {
    operator fun invoke(config: RequestValidationConfig) = config.apply{
        validate<LoginRequest>{ AuthValidation.login(it)}
        validate<UserCreateRequest>{AuthValidation.createUser(it)}
        validate<SchemeCreateRequest>{AdminValidation.createScheme(it)}
    }
}