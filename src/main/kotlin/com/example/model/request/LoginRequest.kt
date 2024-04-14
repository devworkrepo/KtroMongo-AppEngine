package com.example.model.request

import kotlinx.serialization.Serializable

@Serializable
class LoginRequest(
    val mobile : String,
    val password : String
)