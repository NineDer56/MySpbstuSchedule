package com.example.myspbstuschedule.presentation.screens.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myspbstuschedule.domain.usecase.GetGroupScheduleUseCase
import com.example.myspbstuschedule.domain.usecase.GetTeacherScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getGroupScheduleUseCase: GetGroupScheduleUseCase,
    private val getTeacherScheduleUseCase: GetTeacherScheduleUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<ScheduleState>(ScheduleState.Initial)
    val state = _state.asStateFlow()




    fun get7DaysOfWeek(offset : Int = 0) : List<LocalDate> {
        val date = LocalDate.now().plusWeeks(offset.toLong())
        Log.d("Test", "date $date")

        val dayOfWeek = date.dayOfWeek.value
        Log.d("Test", "dayOfWeek $dayOfWeek")

        val mondayDate = if(dayOfWeek != 1) date.minusDays((dayOfWeek - 1).toLong()) else date
        Log.d("Test", "mondayDate $mondayDate")

        val dates = mutableListOf<LocalDate>().apply {
            repeat(7){
                add(mondayDate.plusDays(it.toLong()))
                Log.d("Test", " dates  ${mondayDate.plusDays(it.toLong())}")
            }
        }
        return dates
    }
}