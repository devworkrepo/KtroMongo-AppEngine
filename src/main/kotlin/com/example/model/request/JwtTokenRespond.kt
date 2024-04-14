package com.example.model.request

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date

@Serializable
data class JwtTokenRespond(
    val id : String,
    val userId : String,
    val token : String,
    val createdDate : String,
    val expiryDate : String,
    val isBlackListed : Boolean
)