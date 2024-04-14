package com.example.model.request

import com.example.model.entity.apiService.ApiServiceEntity
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.util.Date


@Serializable
data class ApiServiceRequest(
    val serviceName : String,
    val serviceId : Int,
    val status : Boolean,
){
    fun toApiServiceEntity() = ApiServiceEntity(
        _id = ObjectId(),
        serviceName =  serviceName,
        serviceId = serviceId,
        status = status,
        createdDate = Date(),
        updateDate = Date()
    )
}