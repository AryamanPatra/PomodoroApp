package com.aryaman.pomodoroapp.data.model

import androidx.room.TypeConverter
import java.sql.Date

class DateConverter {

    @TypeConverter
    fun convertDateToString(date: Date): String{
        return date.toString()
    }

    @TypeConverter
    fun convertStringToDate(dateString: String): Date{
        return Date.valueOf(dateString)
    }

}
