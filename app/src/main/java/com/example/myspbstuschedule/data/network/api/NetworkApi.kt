package com.example.myspbstuschedule.data.network.api

import com.example.myspbstuschedule.data.network.dto.GroupResponse
import com.example.myspbstuschedule.data.network.dto.ScheduleNwModel
import com.example.myspbstuschedule.data.network.dto.TeacherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("search/groups")
    suspend fun getGroups(@Query("q") name: String) : GroupResponse

    @GET("scheduler/{groupId}")
    suspend fun getGroupSchedule(
        @Path("groupId") groupId : Int,
        @Query("date") date : String
    ) : ScheduleNwModel

    @GET("search/teachers")
    suspend fun getTeachers(@Query("q") name: String) : TeacherResponse

    @GET("teachers/{teacherId}/scheduler")
    suspend fun getTeacherSchedule(
        @Path("teacherId") teacherId : Int,
        @Query("date") date : String
    ) : ScheduleNwModel
}