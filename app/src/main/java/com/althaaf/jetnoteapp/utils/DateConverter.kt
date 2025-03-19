package com.althaaf.jetnoteapp.utils

import androidx.room.TypeConverter
import java.util.Date


class DateConverter {

    @TypeConverter
    fun longFromDateInput(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun dateFromLongEntity(time: Long): Date {
        return Date(time)
    }
}