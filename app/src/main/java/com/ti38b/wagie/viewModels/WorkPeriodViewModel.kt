package com.ti38b.wagie.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ti38b.wagie.database.DataBaseRepository
import com.ti38b.wagie.database.entities.WorkPeriod
import java.util.*

class WorkPeriodViewModel: ViewModel() {

    private val repository = DataBaseRepository.get()
    val workPeriodsLiveData = repository.getWorkPeriodsList()
    private val workPeriodIdLiveData = MutableLiveData<UUID>()

    var workPeriodLiveData: LiveData<WorkPeriod> = Transformations.switchMap(workPeriodIdLiveData){
        id -> repository.getWorkPeriod(id)
    }

    fun loadData(id: UUID){
        workPeriodIdLiveData.value = id
    }

    fun addWorkPeriod(workPeriod: WorkPeriod){
        repository.addWorkPeriod(workPeriod)
    }
}