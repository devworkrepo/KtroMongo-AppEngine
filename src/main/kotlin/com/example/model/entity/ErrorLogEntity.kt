package com.example.model.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date

data class ErrorLogEntity(
    @BsonId
    val _id : ObjectId,
    val userId : String,
    val route : String,
    val errorMessage : String,
    val stackTrace : String,
    val createdDate : Date
)