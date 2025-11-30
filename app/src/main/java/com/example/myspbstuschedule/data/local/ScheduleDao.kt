package com.example.myspbstuschedule.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.myspbstuschedule.data.local.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query(
        """
        SELECT * 
        FROM schedule_entity 
        WHERE mode = :mode 
            AND id = :id 
            AND :date BETWEEN date_start AND date_end
        LIMIT 1
    """
    )
    suspend fun getSchedule(mode: String, id: Int, date: String): ScheduleEntity?

    @Upsert
    suspend fun upsertSchedule(scheduleEntity: ScheduleEntity)

    @Query(
        """
        DELETE 
        FROM schedule_entity 
        WHERE mode = :mode 
            AND id = :id 
            AND date_start = :dateStart
    """
    )
    suspend fun deleteSchedule(mode: String, id: Int, dateStart: String)
}