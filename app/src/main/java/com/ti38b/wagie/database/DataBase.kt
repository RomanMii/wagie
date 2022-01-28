package com.ti38b.wagie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ti38b.wagie.database.entities.Day
import com.ti38b.wagie.database.entities.WorkPeriod

@Database(entities = [ Day::class, WorkPeriod::class ], version = 0)
@TypeConverters(TypeConverter::class)
abstract class DataBase : RoomDatabase(){
    abstract fun DAO(): DAO
}