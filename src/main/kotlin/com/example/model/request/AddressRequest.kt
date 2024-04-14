package com.example.model.request

import com.example.model.entity.AddressEntity
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class AddressRequest(
    val pinCode : String,
    val city : String,
    val state : String,
    val landmark : String,
){
    fun toAddressEntity() = AddressEntity(
        id = ObjectId(),
        pinCode = pinCode,
        city = city,
        state = state,
        landmark = landmark
    )
}