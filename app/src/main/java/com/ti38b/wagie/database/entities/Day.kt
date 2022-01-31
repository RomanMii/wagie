package com.ti38b.wagie.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "day")
data class Day constructor(@PrimaryKey(autoGenerate = false) val date : Date = Date(),
                           var dayAmount : Double = 0.0,
                           var hours : Int = 0){

}