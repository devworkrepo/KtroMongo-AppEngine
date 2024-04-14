package com.example.model.request

import com.example.model.entity.UserEntity
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId


@Serializable
data class UserCreateRequest(
    val name : String,
    val email : String,
    val mobile : String,
    val age : Int,
    val gender : String,
    val address : AddressRequest,
    val password : String,
    val role : String,
    val parentId : String,
    val apiServices : List<UserApiServiceRequest>
    ){
    fun toUserEntity() = UserEntity(
        _id = ObjectId(),
        name = name,
        mobile = mobile,
        age =  age,
        gender = gender,
        email =  email,
        address = address.toAddressEntity(),
        password =password,
        role =  role,
        parentId = parentId,
        apiServices = apiServices.map { it.toUserApiServiceEntity() }
    )
}


