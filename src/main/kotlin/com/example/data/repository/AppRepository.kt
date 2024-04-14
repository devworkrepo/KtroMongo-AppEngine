package com.example.data.repository

import com.example.model.entity.JwtTokenEntity
import com.example.model.respond.ErrorLogRespond
import com.example.util.RoleRight
import com.example.util.RouteContext
import kotlin.jvm.Throws

interface AppRepository {
    @Throws
    suspend fun checkRoleRight(context: RouteContext, roleRight :RoleRight): Boolean
    suspend fun saveExceptionLog(respond : ErrorLogRespond)
    suspend fun getJwtTokens(userId : String):List<JwtTokenEntity>
    suspend fun getJwtTokenByToken(token : String):JwtTokenEntity?
    suspend fun saveJwtToken(userId: String, token: String) : Boolean
    suspend fun updateJwtToken(token : String) : Boolean
}