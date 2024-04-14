package com.example.model.entity.scheme

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import java.util.Date

data class SchemeEntity(
    @BsonId
    val _id : ObjectId,
    val schemeId : Int,
    val name : String,
    val status : Boolean,
    val schemeDetail : List<SchemeDetailEntity>,
    val createdDate : Date,
    val updateDate : Date
)
