package com.ti38b.wagie.database

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query("SELECT * FROM workPeriod WHERE id=(:id)")
    fun getWorkPeriod(id: UUID): LiveData<WorkPeriod?>

    @Update
    fun updateWorkPeriod(workPeriod: WorkPeriod)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWorkPeriod(workPeriod: WorkPeriod)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDay(day: Day)
}