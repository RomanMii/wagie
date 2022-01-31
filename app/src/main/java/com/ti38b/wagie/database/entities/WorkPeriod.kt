package com.ti38b.wagie.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "workPeriod")
class WorkPeriod constructor(var dayDate: Date,
                             var label : String = "label",
                             var from : String = "00:00",
                             var to : String = "00:00",
                             var periodAmount : Double = 0.0,
                             var workHours : Double = 0.0,
                             @PrimaryKey val id: UUID = UUID.randomUUID()
                             ) {

}