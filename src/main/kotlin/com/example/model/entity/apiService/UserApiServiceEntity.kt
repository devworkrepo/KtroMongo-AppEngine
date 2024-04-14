package com.example.model.entity.apiService

import com.example.model.respond.UserApiServiceRespond
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class UserApiServiceEntity(
    @BsonId
    val _id : ObjectId,
    val apiServiceId: String,
    val status: Boolean
){
    fun toUserApiServiceRespond() = UserApiServiceRespond(
        id = _id.toString(),
        apiServiceId = apiServiceId,
        status = status

    )
}