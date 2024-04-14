package com.example.model.respond

import kotlinx.serialization.Serializable

@Serializable
data class UserApiServiceRespond (
    val id : String,
    val apiServiceId : String,
    val status : Boolean
)