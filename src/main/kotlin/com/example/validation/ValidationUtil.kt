package com.example.validation

import io.ktor.server.plugins.requestvalidation.*
import java.util.regex.Pattern

object ValidationUtil {

    object Validate{
        operator fun invoke(vararg values: String?): ValidationResult {
            val result = values.filterNotNull()
            return if (result.isEmpty()) ValidationResult.Valid
            else ValidationResult.Invalid(result)
        }
    }


    fun validate(condition : Boolean,errorMessage : String): String? {
        if(condition) return null
        else return errorMessage
    }



    private val EMAIL_ADDRESS: Pattern = Pattern.compile(
        "[a-zA-Z0-9+._%\\-]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
    )

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }


    fun validateEmail(email: String): String? {
        return if (isValidEmail(email)) null
        else "Email pattern is invalid"
    }

    fun validateMobile(mobile: String): String? {
        return if ((mobile.startsWith("9") || mobile.startsWith("8") || mobile.startsWith("7") || mobile.startsWith("6")) && mobile.length == 10) null
        else "Mobile number is not valid"
    }


    fun validatePassword(password: String): String? {

        if (password.length < 8) return "Password min length 8 characters"
        if (password.firstOrNull { it.isDigit() } == null) return "Password should not contain first letter in digit"
        if (password.filter { it.isLetter() }
                .firstOrNull { it.isUpperCase() } == null) return "Password should contain a uppercase"
        if (password.filter { it.isLetter() }
                .firstOrNull { it.isLowerCase() } == null) return "Password should contain a lowercase"
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return "Password should contain a digit"
        return null
    }


    fun validateUsername(username: String): String? {
        return if (username.firstOrNull { !it.isLetterOrDigit() } == null) "Username should not contain digit"
        else if (username.length < 3) "Username should contain at least 3 character"
        else null
    }

    fun validateGender(gender: String): String? {
        return if (gender == "Male" || gender == "Female" || gender == "Other") return null
        else null
    }

    fun validateNotEmpty(value : String?,fieldName : String): String? {
        if(value.isNullOrEmpty()) return "$fieldName is required"
        return null
    }



}