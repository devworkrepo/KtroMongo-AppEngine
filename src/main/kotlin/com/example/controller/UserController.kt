package com.example.controller

import com.example.data.repository.UserRepository
import com.example.model.request.UserCreateRequest
import com.example.util.*
import com.example.util.ApiResponse.apiResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.bson.types.ObjectId
import org.koin.core.component.inject

class UserController : BaseController() {

    private val userRepository by inject<UserRepository>()

    suspend fun getUsers(context: RouteContext) = context.apply {


       val userId =  ObjectId(loggedInUserId())

        val users = userRepository.getAllUsers(userId).map { it.toUserRespond() }
        val response = ApiResponse.success(
            message = "All users",
            data = users
        )
        call.respond(response)
    }


    suspend fun createUser(context: RouteContext) = context.apply {


        var request = call.receive<UserCreateRequest>()
        val users = userRepository.getUserByMobileAndEmail(request.mobile, request.email)
        if (users.isNotEmpty())
            return@apply call.respond(ApiResponse.failure(message = ResponseMessage.USER_ALREADY_EXIST))

        val hashPassword = AppUtil.hashPassword(request.password)
        request = request.copy(password = hashPassword)

        val result = userRepository.createUser(request)
        result.insertedId?.let {
            return@apply apiResponse(ApiResponse.success(message = ResponseMessage.USER_CREATED_SUCCESSFULLY))
        } ?: return@apply apiResponse(ApiResponse.failure(message = ResponseMessage.FAILED_TO_CREATE_USER))
    }



}