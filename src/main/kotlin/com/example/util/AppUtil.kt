package com.example.util

import org.mindrot.jbcrypt.BCrypt
import java.util.*


object AppUtil {

    fun hashPassword(password : String) : String {
       return BCrypt.hashpw(password,BCrypt.gensalt())
    }

    fun addHourToCurrentDate(hour : Int): Date {
        val date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.HOUR_OF_DAY, hour)
        val newDate: Date = calendar.time
        return newDate
    }
}