package com.example.model.respond

import com.example.model.entity.ErrorLogEntity
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.util.*

@Serializable
data class ErrorLogRespond(
    val id : String?=null,
    val userId : String?=null,
    val route : String?=null,
    val createdDate : String?= null,
    val errorMessage : String,
    val stackTrace : String
){
    fun toErrorLogEntity() = ErrorLogEntity(
        _id =  ObjectId(),
        userId = userId?:"",
        route = route?:"",
        errorMessage = errorMessage,
        stackTrace = stackTrace,
        createdDate = Date()
    )
}