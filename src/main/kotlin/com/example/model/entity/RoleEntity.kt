package com.example.model.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class RoleEntity(
    @BsonId
    val _id : ObjectId,
    val role : String,
    val rights : List<String>
)