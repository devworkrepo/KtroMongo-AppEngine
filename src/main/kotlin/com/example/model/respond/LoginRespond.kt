package com.example.model.respond

import kotlinx.serialization.Serializable

@Serializable
data class LoginRespond(
    val token : String,
    val user : UserRespond,
)