package com.example.validation

import com.example.model.request.LoginRequest
import com.example.model.request.UserCreateRequest
import com.example.model.request.scheme.SchemeCreateRequest
import io.ktor.server.plugins.requestvalidation.*

object AdminValidation {

    fun createScheme(request: SchemeCreateRequest): ValidationResult = ValidationUtil.Validate(
        ValidationUtil.validateNotEmpty(request.schemeId.toString(),"scheme id required"),
        ValidationUtil.validateNotEmpty(request.name,"scheme name required"),
        ValidationUtil.validateNotEmpty(request.status.toString(),"scheme status required"),
        ValidationUtil.validate(request.schemeDetail.isNotEmpty(),"detail not found")
    )
}