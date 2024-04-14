package com.example.data.implementation

import com.example.data.database.userCollection
import com.example.data.repository.UserRepository
import com.example.model.entity.UserEntity
import com.example.model.request.UserCreateRequest
import com.mongodb.client.model.Filters
import com.mongodb.client.result.InsertOneResult
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl(private val db: MongoDatabase) : UserRepository {


    override suspend fun getUserById(id: ObjectId): UserEntity? {
        return db.userCollection().find(
            Filters.eq(UserEntity::_id.name, id)
        ).firstOrNull()
    }

    override suspend fun getUserByMobileAndPassword(mobile: String, password: String): UserEntity? {
        val collection = db.userCollection()

        val query = Filters.and(
            Filters.eq(UserEntity::mobile.name, mobile)
        )

        val userEntity = collection.find(query).limit(1).firstOrNull()
        if (userEntity != null)
            if (!BCrypt.checkpw(password, userEntity.password)) return null
        return userEntity
    }

    override suspend fun getAllUsers(id : ObjectId): List<UserEntity> {
        return db.userCollection().find(
            Filters.and(
                Filters.eq(UserEntity::parentId.name,id.toString()),
                Filters.ne(UserEntity::_id.name,id)
            )
        ).toList()
    }

    override suspend fun createUser(request: UserCreateRequest): InsertOneResult {

        return db.userCollection().insertOne(request.toUserEntity())
    }

    override suspend fun getUserByMobileAndEmail(mobile: String, email: String): List<UserEntity> {
        val query = Filters.and(
            Filters.eq(UserEntity::mobile.name, mobile),
            Filters.eq(UserEntity::email.name, email),
        )

        return db.userCollection().find(query).toList()
    }


}