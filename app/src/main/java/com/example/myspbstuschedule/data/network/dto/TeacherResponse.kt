package com.example.myspbstuschedule.data.network.dto

import com.google.gson.annotations.SerializedName

data class TeacherResponse(
    @SerializedName("teachers")
    val teachers : List<TeacherNwModel>
)
