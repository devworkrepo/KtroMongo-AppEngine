package com.example.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.repository.AppRepository
import com.example.util.SystemEnv
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.inject
import java.util.*

object AuthConfig {

    private const val CLAIM = "USER"
    private val algorithm = Algorithm.HMAC256(SystemEnv.JWT.SECRET)


    operator fun invoke(config: JWTAuthenticationProvider.Config) = config.apply {
        realm = SystemEnv.JWT.REALM
        verifier(this)
        validate(this)
    }


    private fun verifier(config: JWTAuthenticationProvider.Config) {
        config.verifier(
            JWT.require(algorithm)
                .withIssuer(SystemEnv.JWT.ISSUER)
                .withAudience(SystemEnv.JWT.AUDIENCE)
                .build()
        )
    }


    private fun validate(config: JWTAuthenticationProvider.Config) {
        config.validate {
            val token = request.headers["Authorization"]?.removePrefix("Bearer ")
                ?: throw Exception("token not found for validation")
            val appRepository by inject<AppRepository>()
            val jwtToken = appRepository.getJwtTokenByToken(token)
                ?: throw Exception("token not found from db for validation")

            if (jwtToken.isBlackListed) null
            else {
                val claim = it.payload.getClaim(CLAIM).asString()
//            val json = Json { encodeDefaults = true }
//            val user = json.decodeFromString<UserRespond>(claim)
                UserIdPrincipal(claim)
            }


        }
    }


    fun createToken(userId: String): String {

//        val json = Json { encodeDefaults = true }
//        val userJson = json.encodeToString(user)


        val oneSecond = 1000
        val oneMinute = 60 * oneSecond
        val oneHour = 60 * oneMinute

        val token = JWT.create()
            .withIssuer(SystemEnv.JWT.ISSUER)
            .withAudience(SystemEnv.JWT.AUDIENCE)
            .withClaim(CLAIM, userId)
            .withExpiresAt(Date(System.currentTimeMillis() + (8 * oneHour)))
            .sign(algorithm)
        return token
    }

    data class UserIdPrincipal(val userId: String) : Principal
}