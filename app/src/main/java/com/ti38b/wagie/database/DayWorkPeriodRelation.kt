package com.ti38b.wagie.database

import androidx.room.Embedded
import androidx.room.Relation
import com.ti38b.wagie.database.entities.Day
import com.ti38b.wagie.database.entities.WorkPeriod

data class DayWorkPeriodsRelation (
    @Embedded val day: Day,
    @Relation(
        parentColumn = "id",
        entityColumn = "dayId"
    )
    val workPeriods: List<WorkPeriod>
)