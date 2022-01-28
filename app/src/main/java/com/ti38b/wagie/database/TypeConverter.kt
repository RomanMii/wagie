package com.ti38b.wagie.database

import androidx.room.TypeConverter
import java.util.*

class TypeConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date?{
        return millisSinceEpoch?.let{
            Date(it)
        }
    }
}