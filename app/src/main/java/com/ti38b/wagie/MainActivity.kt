package com.ti38b.wagie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.ti38b.wagie.database.entities.Day
import com.ti38b.wagie.database.entities.WorkPeriod
import com.ti38b.wagie.viewModels.DaysViewModel
import com.ti38b.wagie.viewModels.WorkPeriodViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var title: EditText
    private lateinit var timeFrom: EditText
    private lateinit var timeTo: EditText
    private lateinit var amount: EditText
    private lateinit var hourIncome: EditText
    private lateinit var addButton: Button

    private val workPeriodViewModel: WorkPeriodViewModel by lazy{
        ViewModelProvider(this).get(WorkPeriodViewModel::class.java)
    }

    private val daysViewModel: DaysViewModel by lazy {
        ViewModelProvider(this).get(DaysViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.title_field)
        timeFrom = findViewById(R.id.time_from_field)
        timeTo = findViewById(R.id.time_to_field)
        amount = findViewById(R.id.amount_field)
        hourIncome = findViewById(R.id.hour_income_field)
        addButton = findViewById(R.id.add_button)

        addButton.setOnClickListener{view: View ->
            //TODO add workPeriod
            val titleString: String = title.text.toString()
            val fromString: String = timeFrom.text.toString()
            val toString: String = timeTo.text.toString()
            val workHours: Double = hourCalculate(fromString, toString)
            val amount: Double = amountCalculate(workHours, hourIncome.text.toString().toDouble())
            addWorkPeriod(titleString,fromString,toString,amount,workHours)
        }

        Log.i("work periods",workPeriodViewModel.workPeriodsLiveData.value.toString())
        Log.i("days",daysViewModel.dayListLiveData.value.toString())

    }

    private fun hourCalculate(from: String, to: String): Double{
        val workingHours = getHoursFromString(to) - getHoursFromString(from)
        val workingMinutes = getMinutesFromString(to) - getMinutesFromString(from)
        return workingHours + (workingMinutes/60.0)
    }

    private fun getHoursFromString(time: String): Int{
        val index = time.indexOf(':')
        return time.substring(0,index).toInt()
    }

    private fun getMinutesFromString(time: String): Int{
        val index = time.indexOf(':')
        return time.substring(index+1).toInt()
    }

    private fun amountCalculate(workHours: Double, hourIncome: Double): Double{
        return workHours * hourIncome
    }

    private fun addWorkPeriod(title: String, from: String, to: String, amount: Double, workHours: Double){
        val today = Date()
        daysViewModel.loadData(today)
        val date = daysViewModel.dayLiveData.value?.date
        if(date != null){
            //day exist
            val workPeriod = WorkPeriod(date,title,from,to,amount, workHours)
            workPeriodViewModel.addWorkPeriod(workPeriod)
        }else{
            //day doesn't exist
            val today = Day()
            daysViewModel.addDay(today)
            val workPeriod = WorkPeriod(today.date,title,from,to,amount,workHours)
            workPeriodViewModel.addWorkPeriod(workPeriod)
        }
    }
}