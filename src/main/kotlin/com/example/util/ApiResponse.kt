package com.example.util

import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

object ApiResponse {


    private fun getStatus(status: ResponseStatus) = when (status) {
        ResponseStatus.SUCCESS -> 1
        ResponseStatus.FAILURE -> 2
        ResponseStatus.VALIDATION -> -3
        ResponseStatus.EXCEPTION -> 4
    }


    fun success(message: String = "Success") = Response(getStatus(ResponseStatus.SUCCESS), message)


    fun <T> success(
        message: String = "Success",
        data: T?
    ) = ResponseData(getStatus(ResponseStatus.SUCCESS), message, data)


    fun failure(
        message: String = "Failure",
    ) = Response(getStatus(ResponseStatus.FAILURE), message)




    fun <T> failure(
        message: String = "Failure",
        data: T?
    ) = ResponseData(getStatus(ResponseStatus.FAILURE), message, data)

    fun <T> exception(
        message: String = "Failure - Internal Server Error",
        data: T?
    ) = ResponseData(getStatus(ResponseStatus.EXCEPTION), message, data)

    fun validation(
        message: String = "Validation Failed",
    ) = Response(getStatus(ResponseStatus.VALIDATION), message)
    fun <T> validation(
        message: String = "Validation Failed",
        data: T?
    ) = ResponseData(getStatus(ResponseStatus.VALIDATION), message, data)





    enum class ResponseStatus {
        SUCCESS, FAILURE,VALIDATION,EXCEPTION
    }


    @Serializable
    data class Response(
        val status: Int,
        val message: String,
    )

    @Serializable
    data class ResponseData<T>(
        val status: Int,
        val message: String,
        val data: T? = null
    )

   suspend inline fun <reified T : Any > RouteContext.apiResponse(value : T){
        call.respond(value)
    }

}