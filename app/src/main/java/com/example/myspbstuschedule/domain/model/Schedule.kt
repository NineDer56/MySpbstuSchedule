package com.example.myspbstuschedule.domain.model

data class Schedule (
    val week: Week,
    val days : List<Day>
)