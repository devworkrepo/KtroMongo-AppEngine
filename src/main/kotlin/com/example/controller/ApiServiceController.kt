package com.example.controller

import com.example.data.repository.ApiServiceRepository
import com.example.data.repository.UserRepository
import com.example.model.request.ApiServiceRequest
import com.example.util.ApiResponse
import com.example.util.ApiResponse.apiResponse
import com.example.util.RouteContext
import com.example.util.loggedInUserId
import io.ktor.server.application.*
import io.ktor.server.request.*
import org.koin.core.component.inject

class ApiServiceController : BaseController() {

   private val repository by inject<ApiServiceRepository> ()

    suspend fun create(pipelineContext: RouteContext) = pipelineContext.apply {

        val request = call.receive<ApiServiceRequest>()
        val result = repository.createApiService(request)
        result.insertedId?.let {
            return@apply apiResponse(ApiResponse.success())
        }
    }
}