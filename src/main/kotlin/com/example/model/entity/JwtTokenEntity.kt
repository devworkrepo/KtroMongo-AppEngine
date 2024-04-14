package com.example.model.entity

import com.example.model.request.JwtTokenRespond
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date

data class JwtTokenEntity(
    @BsonId
    val _id : ObjectId,
    val userId : String,
    val token : String,
    val createdDate : Date,
    val expiryDate : Date,
    var isBlackListed : Boolean = false
){
    fun toJwtTokenRespond() = JwtTokenRespond(
        id = _id.toString(),
        userId = userId,
        token = token,
        createdDate = createdDate.toString(),
        expiryDate = expiryDate.toString(),
        isBlackListed = false
    )
}