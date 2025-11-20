package com.example.myspbstuschedule.domain.model

data class Day(
    val weekday : Int,
    val date : String,
    val lessons : List<Lesson>
)