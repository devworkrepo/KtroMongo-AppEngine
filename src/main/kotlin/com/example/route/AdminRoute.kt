package com.example.route

import com.example.controller.AdminController
import com.example.util.RoleRight
import com.example.util.checkRoleRight
import com.example.util.postCallWithRoleCheck
import io.ktor.server.routing.*

fun Route.adminRoutes(){

    val controller = AdminController()

    postCallWithRoleCheck("/create-scheme",RoleRight.CreateScheme){
        controller.createScheme(it)
    }

}