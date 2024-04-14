package com.example.model.respond

import kotlinx.serialization.Serializable

@Serializable
data class UserRespond(
    val id : String,
    val name : String,
    val mobile : String,
    val email : String,
    val gender : String,
    val age : Int,
    val address : AddressRespond,
    val role : String,
    val parentId : String,
    val apiServices : List<UserApiServiceRespond>?=null
)
