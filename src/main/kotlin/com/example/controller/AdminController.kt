package com.example.controller

import com.example.model.request.scheme.SchemeCreateRequest
import com.example.util.ApiResponse
import com.example.util.ApiResponse.apiResponse
import com.example.util.RouteContext
import io.ktor.server.application.*
import io.ktor.server.request.*
import org.koin.core.component.inject

class AdminController : BaseController() {

    private val adminRepository by inject<AdminRepository>()

    suspend fun createScheme(context: RouteContext) = context.apply {
        val request = call.receive<SchemeCreateRequest>()
        val result = adminRepository.createScheme(request)
        return@apply apiResponse(if(result) ApiResponse.success() else ApiResponse.failure())
    }
}