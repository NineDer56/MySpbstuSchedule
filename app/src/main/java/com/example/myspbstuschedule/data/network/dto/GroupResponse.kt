package com.example.myspbstuschedule.data.network.dto

import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("groups")
    val groups : List<GroupNwModel>
)
