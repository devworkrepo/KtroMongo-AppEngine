package com.example.model.request.scheme

import com.example.model.entity.scheme.SchemeEntity
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date


@Serializable
data class SchemeCreateRequest(
    val schemeId : Int,
    val name : String,
    val status : Boolean,
    val schemeDetail : List<SchemeDetailRequest>,
){
    fun toSchemeEntity()= SchemeEntity(
        _id = ObjectId(),
        schemeId = schemeId,
        name = name,
        status = status,
        schemeDetail = schemeDetail.map { it.toSchemeDetailEntity() },
        createdDate = Date(),
        updateDate = Date()
    )
}
