package com.example.util

object AppConstant {
    const val JWT_TOKEN_EXPIRY_HOUR = 8
}

object SystemEnv {
    object JWT {
        val AUDIENCE = System.getenv("JWT_AUDIENCE") ?: "test_audience"
        val ISSUER = System.getenv("JWT_ISSUER") ?: "test_issuer"
        val REALM = System.getenv("JWT_REALM") ?: "test_realm"
        val SECRET = System.getenv("JWT_SECRET") ?: "test_password"
    }
    object Mongodb {
         val MONGODB_USER = System.getenv("MONGODB_USER") ?: ""
         val MONGODB_PASSWORD = System.getenv("MONGODB_PASSWORD") ?: ""
         val MONGODB_DATABASE = System.getenv("MONGODB_DATABASE") ?: "testdb"
    }
}

sealed class RoleRight(val name : String){
    data object CreateUser : RoleRight("userCreate")
    data object CreateApiService : RoleRight("apiServiceCreate")
    data object GetLoginSessions : RoleRight("getLoginSessions")
    data object CreateScheme : RoleRight("schemeCreate")
}

object CollectionNames {
    const val USER = "users"
    const val API_SERVICE = "apiServices"
    const val ROLE = "roles"
    const val ERROR_LOG = "errorLogs"
    const val JWT_TOKENS = "jwtTokens"
    const val SCHEME = "schemes"
}