package com.example.controller

import com.example.config.AuthConfig
import com.example.data.repository.AppRepository
import com.example.data.repository.UserRepository
import com.example.model.request.LoginRequest
import com.example.model.respond.LoginRespond
import com.example.util.*
import com.example.util.ApiResponse.apiResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.coroutines.flow.merge
import org.koin.core.component.inject

class AuthController : BaseController() {

    private val userRepository by inject<UserRepository>()
    private val appRepository by inject<AppRepository>()

    suspend fun login(context: RouteContext) = context.apply {

        val request = call.receive<LoginRequest>()
        val mobile = request.mobile
        val password = request.password

        val user = userRepository.getUserByMobileAndPassword(mobile, password)
            ?: return@apply call.respond(ApiResponse.failure(message = ResponseMessage.USER_NOT_FOUND))

        val token = AuthConfig.createToken(user._id.toString())


        val isTokenSaved = appRepository.saveJwtToken(user._id.toString(), token)
        if (!isTokenSaved) return@apply apiResponse(ApiResponse.failure(message = "unable to save session token"))

        val response = ApiResponse.success(
            message = ResponseMessage.LOGIN_SUCCESSFUL,
            data = LoginRespond(
                token = token,
                user = user.toUserRespond()
            )
        )


        return@apply call.respond(response)
    }


    suspend fun getJwtTokens(context: RouteContext) = context.apply {
        val userId = loggedInUserId()
        val tokens = appRepository.getJwtTokens(userId).map { it.toJwtTokenRespond() }
        return@apply apiResponse(tokens)
    }

    suspend fun logout(context: RouteContext) = context.apply {
        val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
            ?: throw Exception("Token not found for logout")
        val isLogout = appRepository.updateJwtToken(token)
        if(isLogout) return@apply apiResponse(ApiResponse.success(message = "logout successfully"))
        else return@apply apiResponse(ApiResponse.failure(message = "something went wrong! failed to logout"))
    }


}