package com.example.model.respond

import kotlinx.serialization.Serializable

@Serializable
data class AddressRespond(
    val id : String,
    val pinCode : String,
    val city : String,
    val state : String,
    val landmark : String
)