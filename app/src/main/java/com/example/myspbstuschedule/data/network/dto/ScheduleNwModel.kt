package com.example.myspbstuschedule.data.network.dto

import com.google.gson.annotations.SerializedName


data class ScheduleNwModel (
    @SerializedName("week")
    val week: WeekNwModel,
    @SerializedName("days")
    val days : List<DayNwModel>
)