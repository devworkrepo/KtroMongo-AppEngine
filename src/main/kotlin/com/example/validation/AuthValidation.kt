package com.example.validation

import com.example.model.request.LoginRequest
import com.example.model.request.UserCreateRequest
import io.ktor.server.plugins.requestvalidation.*

object AuthValidation {

    fun login(request: LoginRequest): ValidationResult = ValidationUtil.Validate(
        ValidationUtil.validateMobile(request.mobile),
        ValidationUtil.validatePassword(request.password)
    )

    fun createUser(request: UserCreateRequest): ValidationResult = ValidationUtil.Validate(
        ValidationUtil.validateUsername(request.name),
        ValidationUtil.validateMobile(request.mobile),
        ValidationUtil.validateEmail(request.email),
        ValidationUtil.validate(request.age >=18,"Age must be 18+"),
        ValidationUtil.validateGender(request.gender),
        ValidationUtil.validateNotEmpty(request.address.city,"City"),
        ValidationUtil.validateNotEmpty(request.address.pinCode,"Pin Code"),
        ValidationUtil.validateNotEmpty(request.address.state,"State"),
        ValidationUtil.validateNotEmpty(request.address.state,"Landmark"),
        ValidationUtil.validateNotEmpty(request.role,"Role"),
        ValidationUtil.validateNotEmpty(request.parentId,"ParentId"),
        ValidationUtil.validate(request.apiServices.isNotEmpty(),"User service is required")
    )
}