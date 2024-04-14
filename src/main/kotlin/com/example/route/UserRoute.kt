package com.example.route

import com.example.controller.UserController
import com.example.data.repository.AppRepository
import com.example.util.RoleRight
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes(){

    val controller = UserController()
    val appRepository by inject<AppRepository>()

    get("/all"){controller.getUsers(this)}

    post ("/create-user"){
        appRepository.checkRoleRight(this,RoleRight.CreateUser)
        controller.createUser(this)
    }

}