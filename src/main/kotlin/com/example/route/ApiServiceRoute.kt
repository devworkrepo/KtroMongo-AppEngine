package com.example.route

import com.example.controller.ApiServiceController
import com.example.data.repository.AppRepository
import com.example.util.RoleRight
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.apiServiceRoutes(){

    val controller = ApiServiceController()
    val appRepository by inject<AppRepository>()

    post("/create") {
        appRepository.checkRoleRight(this,RoleRight.CreateApiService)
        controller.create(this)
    }

}