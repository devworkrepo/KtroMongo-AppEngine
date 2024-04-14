package com.example.model.entity

import com.example.model.entity.apiService.UserApiServiceEntity
import com.example.model.respond.UserRespond
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class UserEntity(
    @BsonId val _id : ObjectId,
    val name : String,
    val mobile : String,
    val email : String,
    val age : Int,
    val gender : String,
    val password : String,
    val address : AddressEntity,
    val role : String,
    val parentId : String,
    val apiServices: List<UserApiServiceEntity>?
){
    fun toUserRespond() = UserRespond(
        id = _id.toString(),
        name = name,
        mobile = mobile,
        email = email,
        age = age,
        gender = gender,
        address = address.toAddressRespond(),
        role  = role,
        parentId = parentId,
        apiServices = apiServices?.map { it.toUserApiServiceRespond() }
    )
}
