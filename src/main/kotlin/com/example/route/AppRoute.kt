package com.example.route

import com.example.util.SystemEnv
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonObject


fun Application.configureRouting() {

    routing {
        route("/auth"){authRoutes()}
        authenticate {
            route("/user"){userRoutes()}
            route("/api-service"){apiServiceRoutes()}
            route("/admin"){adminRoutes()}
        }
        post ("/test"){

          val data =   call.receive<JsonObject>()
            call.respond(data)
        }
        get("/") { call.respondText { "Hello dev ${SystemEnv.Mongodb.MONGODB_USER}" } }

    }
}