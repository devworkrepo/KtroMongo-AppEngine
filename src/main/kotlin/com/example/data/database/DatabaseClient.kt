package com.example.data.database

import com.example.util.SystemEnv
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

object DatabaseClient {

    private fun mongodbConnectionString(): String {
        val user = SystemEnv.Mongodb.MONGODB_USER
        val pass = SystemEnv.Mongodb.MONGODB_PASSWORD
        val isDeployment = user.isNotEmpty() && pass.isNotEmpty()

        return if (isDeployment) "mongodb+srv://$user:$pass@cluster0.jyxy8s5.mongodb.net/"
        else "mongodb://localhost:27017"
    }

    private fun mongodbClient(): MongoClient {
        return  MongoClient.create(mongodbConnectionString())
    }

    fun getDatabase(): MongoDatabase {
        return mongodbClient().getDatabase(SystemEnv.Mongodb.MONGODB_DATABASE)
    }
}
