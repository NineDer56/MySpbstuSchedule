package com.example.myspbstuschedule.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.myspbstuschedule.data.local.pojo.WeekWithDays
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Transaction
    @Query(
        """
            SELECT * FROM week
            WHERE :date BETWEEN date_start AND date_end
            LIMIT 1
        """
    )
    fun getScheduleByDate(date : String) : Flow<WeekWithDays?>
}