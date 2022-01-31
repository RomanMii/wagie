package com.ti38b.wagie.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.ti38b.wagie.database.entities.Day
import com.ti38b.wagie.database.entities.WorkPeriod
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "wagie-database"
class DataBaseRepository private constructor(context: Context){

    private val database: DataBase =
        Room.databaseBuilder(
            context.applicationContext,
            DataBase::class.java,
            DATABASE_NAME
        ).build()

    private val dao = database.DAO()
    private val executor = Executors.newSingleThreadExecutor()

    fun getWorkPeriodsList(): LiveData<List<WorkPeriod>> = dao.getWorkPeriodsOfDay(Date())
    fun getWorkPeriod(id: UUID): LiveData<WorkPeriod?> = dao.getWorkPeriod(id)
    fun getDaysList(): LiveData<List<Day>> = dao.getDays()
    fun getDay(date: Date): LiveData<Day?> = dao.getDay(date)

    fun addDay(day: Day){
        executor.execute{
            dao.addDay(day)
        }
    }

    fun addWorkPeriod(workPeriod: WorkPeriod){
        executor.execute {
            dao.addWorkPeriod(workPeriod)
        }
    }

    fun updateWorkPeriod(workPeriod: WorkPeriod){
        executor.execute {
            dao.updateWorkPeriod(workPeriod)
        }
    }

    companion object{
        private var INSTANCE: DataBaseRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = DataBaseRepository(context)
            }
        }

        fun get(): DataBaseRepository{
            return INSTANCE ?: throw IllegalStateException("Repository must be initialized")
        }
    }
}