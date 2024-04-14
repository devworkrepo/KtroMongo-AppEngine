package com.example.data.implementation

import com.example.data.database.errorLogCollection
import com.example.data.database.jwtTokeCollection
import com.example.data.database.roleCollection
import com.example.data.repository.AppRepository
import com.example.data.repository.UserRepository
import com.example.model.entity.JwtTokenEntity
import com.example.model.entity.RoleEntity
import com.example.model.respond.ErrorLogRespond
import com.example.util.*
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import java.util.*
import kotlin.jvm.Throws

class AppRepositoryIml(
    private val userRepository: UserRepository,
    private val db: MongoDatabase
) : AppRepository {

    @Throws
    override suspend fun checkRoleRight(context: RouteContext, roleRight: RoleRight): Boolean {
        val userId = context.loggedInUserId()
        val user = userRepository.getUserById(ObjectId(userId)) ?: throw Exception(ResponseMessage.USER_NOT_FOUND)
        val role = db.roleCollection().find(Filters.eq(RoleEntity::role.name, user.role)).firstOrNull()
            ?: throw Exception(ResponseMessage.ROLE_NOT_FOUND + " : $userId")
        if (!role.rights.contains(roleRight.name)) throw Exception(ResponseMessage.ROLE_RIGHT_NOT_FOUND + " : ${user.role}")
        return true
    }

    override suspend fun saveExceptionLog(respond: ErrorLogRespond) {
        db.errorLogCollection().insertOne(respond.toErrorLogEntity())
    }

    override suspend fun getJwtTokens(userId: String): List<JwtTokenEntity> {
        return db.jwtTokeCollection().find(Filters.eq(JwtTokenEntity::userId.name, userId)).toList()
    }

    override suspend fun getJwtTokenByToken(token: String): JwtTokenEntity? {
        return db.jwtTokeCollection().find(Filters.eq(JwtTokenEntity::token.name, token)).firstOrNull()
    }

    override suspend fun saveJwtToken(userId: String, token: String): Boolean {
        val entity = JwtTokenEntity(
            _id = ObjectId(),
            userId = userId,
            token = token,
            createdDate = Date(),
            expiryDate = AppUtil.addHourToCurrentDate(AppConstant.JWT_TOKEN_EXPIRY_HOUR),
            isBlackListed = false
        )
        val result = db.jwtTokeCollection().insertOne(entity)
        return result.insertedId != null
    }

    override suspend fun updateJwtToken(token: String): Boolean {
        val tokeEntity = getJwtTokenByToken(token) ?: throw Exception("token not found from db to logout")
        val collection = db.jwtTokeCollection()
        tokeEntity.isBlackListed = true
        val result = collection.findOneAndUpdate(
            Filters.eq(JwtTokenEntity::_id.name, tokeEntity._id),
            Updates.set(JwtTokenEntity::isBlackListed.name, true)
        )
        return result!=null
    }
}