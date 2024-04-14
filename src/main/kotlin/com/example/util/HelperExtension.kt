package com.example.util

import com.example.config.AuthConfig
import com.example.data.repository.AppRepository
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Indexes
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.jvm.Throws


inline fun <reified T : Any> withTry(throwException : Boolean = true,scope : ()->T): T? {
    try {
       val result =  scope.invoke()
        return result
    }catch (e : Exception){
        if(throwException) throw e
        else return null
    }
}

@Throws
fun RouteContext.loggedInUserId()  : String {
    val principal = call.principal<AuthConfig.UserIdPrincipal>() ?: throw Exception("logged in validation failed")
    return principal.userId
}

@Throws
fun ApplicationCall.loggedInUserId()  : String {
    val principal = principal<AuthConfig.UserIdPrincipal>() ?: throw Exception("logged in validation failed")
    return principal.userId
}


inline fun <reified T : Any> MongoDatabase.getDBCollection(collectionName : String) : MongoCollection<T> {
    return  this.getCollection<T>(collectionName)
}



suspend inline fun <reified T : Any> MongoDatabase.createIndexFor(collectionName: String, name : String){
    getCollection<T>(collectionName).createIndex(
        Indexes.ascending(name),
        IndexOptions().unique(true)
    )
}

inline fun Route.postCallWithRoleCheck(endPoint : String, role : RoleRight,crossinline scope: suspend (RouteContext) -> Unit){
    post(endPoint){
        this.checkRoleRight(role)
        scope.invoke(this)
    }
}

suspend fun RouteContext.checkRoleRight(
    role: RoleRight) {
    this.call.apply {
        val appRepository by inject<AppRepository>()
        appRepository.checkRoleRight(this@checkRoleRight,role)
    }

}