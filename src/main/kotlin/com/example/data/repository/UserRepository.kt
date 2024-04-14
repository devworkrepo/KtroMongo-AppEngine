package com.example.data.repository

import com.example.model.entity.UserEntity
import com.example.model.request.UserCreateRequest
import com.mongodb.client.result.InsertOneResult
import org.bson.types.ObjectId

interface UserRepository {
    suspend fun getUserById(id : ObjectId) : UserEntity?
    suspend fun getUserByMobileAndPassword(mobile: String, password: String): UserEntity?
    suspend fun getAllUsers(id : ObjectId): List<UserEntity>
    suspend fun createUser(request: UserCreateRequest): InsertOneResult
    suspend fun getUserByMobileAndEmail(mobile: String, email: String) : List<UserEntity>
}