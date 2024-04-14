package com.example.model.entity.apiService

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date

data class ApiServiceEntity(
    @BsonId
    val _id : ObjectId,
    val serviceId : Int,
    val serviceName : String,
    val status : Boolean,
    val createdDate : Date,
    val updateDate: Date,
)