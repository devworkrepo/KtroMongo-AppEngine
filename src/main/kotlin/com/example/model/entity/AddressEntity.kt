package com.example.model.entity

import com.example.model.respond.AddressRespond
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class AddressEntity(
    @BsonId
    val id : ObjectId,
    val pinCode : String,
    val city : String,
    val state : String,
    val landmark : String,
){
    fun toAddressRespond() = AddressRespond(
        id = id.toString(),
        pinCode = pinCode,
        city = city,
        state = state,
        landmark = landmark
    )
}