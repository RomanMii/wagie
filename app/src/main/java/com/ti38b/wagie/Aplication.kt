package com.ti38b.wagie

import android.app.Application
import com.ti38b.wagie.database.DataBaseRepository

class Aplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DataBaseRepository.initialize(this)
    }
}