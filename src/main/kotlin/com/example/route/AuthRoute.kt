package com.example.route

import com.example.controller.AuthController
import com.example.data.repository.AppRepository
import com.example.util.RoleRight
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRoutes(){
    val controller = AuthController()
    val appRepository by inject<AppRepository>()

    post("/login") { controller.login(this) }

    authenticate {
        get("/login-sessions") {
            appRepository.checkRoleRight(this,RoleRight.GetLoginSessions)
            controller.getJwtTokens(this)
        }
        get("/logout") { controller.logout(this) }
    }
}