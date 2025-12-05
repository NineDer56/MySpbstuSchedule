package com.example.myspbstuschedule.data.network.dto

import com.google.gson.annotations.SerializedName


data class AuditoryNwModel(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("building")
    val building: BuildingNwModel
)
