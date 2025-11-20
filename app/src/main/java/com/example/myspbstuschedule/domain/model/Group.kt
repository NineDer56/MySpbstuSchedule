package com.example.myspbstuschedule.domain.model

data class Group(
    val id : Int,
    val name : String,
    val level : Int,
    val faculty: Faculty
)