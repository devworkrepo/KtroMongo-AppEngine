package com.example.controller

import com.example.data.database.schemeCollection
import com.example.data.database.userCollection
import com.example.model.request.scheme.SchemeCreateRequest
import com.mongodb.kotlin.client.coroutine.MongoDatabase

class AdminRepositoryImpl(
    private val db: MongoDatabase
) : AdminRepository {
    override suspend fun createScheme(request: SchemeCreateRequest): Boolean {
        val result = db.schemeCollection().insertOne(request.toSchemeEntity())
        return  result.insertedId != null
    }
}