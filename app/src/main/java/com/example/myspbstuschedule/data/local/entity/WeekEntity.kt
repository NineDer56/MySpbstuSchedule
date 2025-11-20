package com.example.myspbstuschedule.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "week")
data class WeekEntity(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo("date_start") val dateStart : String ,
    @ColumnInfo("date_end") val dateEnd : String,
    @ColumnInfo("id_odd") val idOdd : String
)
