package com.example.data.implementation

import com.example.data.database.apiServiceCollection
import com.example.data.database.userCollection
import com.example.data.repository.ApiServiceRepository
import com.example.model.request.ApiServiceRequest
import com.mongodb.client.result.InsertOneResult
import com.mongodb.kotlin.client.coroutine.MongoDatabase

class ApiServiceRepositoryImpl(private val db : MongoDatabase) : ApiServiceRepository {


    override suspend fun createApiService(request : ApiServiceRequest): InsertOneResult {

       return db.apiServiceCollection().insertOne(request.toApiServiceEntity())
    }
}