package com.ti38b.wagie.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ti38b.wagie.database.DataBaseRepository
import com.ti38b.wagie.database.entities.Day
import java.util.*

class DaysViewModel: ViewModel() {

    private val repository = DataBaseRepository.get()
    val dayListLiveData = repository.getDaysList()
    private val dateLiveData = MutableLiveData<Date>()

    var dayLiveData: LiveData<Day?> = Transformations.switchMap(dateLiveData){
        date -> repository.getDay(date)
    }

    fun loadData(date: Date){
        dateLiveData.value = date
    }

    fun addDay(day: Day){
        repository.addDay(day)
    }
}