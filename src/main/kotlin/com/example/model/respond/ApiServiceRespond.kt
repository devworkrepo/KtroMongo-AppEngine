package com.example.model.respond

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.*

@Serializable
data class ApiServiceRespond(
    val id : String,
    val serviceName : String,
    val serviceId : Int,
    val status : Boolean,
    val createdDate : String,
    val updateDate: String,
)