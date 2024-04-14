package com.example.model.request

import com.example.model.entity.apiService.UserApiServiceEntity
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class UserApiServiceRequest(
    val apiServiceId : String,
    val status : Boolean
){
    fun toUserApiServiceEntity() = UserApiServiceEntity(
        _id = ObjectId(),
        apiServiceId = apiServiceId,
        status = status
    )
}