package com.example.data.repository

import com.example.model.request.ApiServiceRequest
import com.mongodb.client.result.InsertOneResult

interface ApiServiceRepository {
    suspend fun createApiService(request : ApiServiceRequest) : InsertOneResult
}