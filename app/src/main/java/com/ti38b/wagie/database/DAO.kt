package com.ti38b.wagie.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ti38b.wagie.database.entities.Day
import com.ti38b.wagie.database.entities.WorkPeriod
import java.util.*

@Dao
interface DAO {
    @Query("SELECT * FROM day")
    fun getDays(): LiveData<List<Day>>

    @Query("SELECT * FROM day WHERE date=(:date)")
    fun getDay(date: Date): LiveData<Day?>

    @Query("SELECT * FROM workperiod WHERE dayDate=(:date)")
    fun getWorkPeriodsOfDay(date: Date): LiveData<List<WorkPeriod>>

    @Update
    fun updateWorkPeriod(workPeriod: WorkPeriod)

    @Insert
    fun addWorkPeriod(workPeriod: WorkPeriod)

    @Insert
    fun addDay(day: Day)
}