package com.example.myspbstuschedule.data.network.dto

import com.google.gson.annotations.SerializedName

data class LessonTypeNwModel(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("abbr")
    val abbr : String
)