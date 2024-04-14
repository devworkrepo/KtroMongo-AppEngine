package com.example.data.database

import com.example.model.entity.*
import com.example.model.entity.apiService.ApiServiceEntity
import com.example.model.entity.scheme.SchemeEntity
import com.example.util.CollectionNames
import com.example.util.createIndexFor
import com.example.util.getDBCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase


fun MongoDatabase.userCollection() = this.getDBCollection<UserEntity>(CollectionNames.USER)
fun MongoDatabase.apiServiceCollection() = this.getDBCollection<ApiServiceEntity>(CollectionNames.API_SERVICE)
fun MongoDatabase.roleCollection() = this.getDBCollection<RoleEntity>(CollectionNames.ROLE)
fun MongoDatabase.errorLogCollection() = this.getDBCollection<ErrorLogEntity>(CollectionNames.ERROR_LOG)
fun MongoDatabase.jwtTokeCollection() = this.getDBCollection<JwtTokenEntity>(CollectionNames.JWT_TOKENS)
fun MongoDatabase.schemeCollection() = this.getDBCollection<SchemeEntity>(CollectionNames.SCHEME)

suspend fun MongoDatabase.userIndexFor(name: String) =
    createIndexFor<UserEntity>(CollectionNames.USER, name)



