package com.example.controller

import com.example.model.request.scheme.SchemeCreateRequest

interface AdminRepository {
    suspend fun createScheme(request: SchemeCreateRequest): Boolean
}