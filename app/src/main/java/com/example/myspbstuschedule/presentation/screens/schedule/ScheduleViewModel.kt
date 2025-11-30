package com.example.myspbstuschedule.presentation.screens.schedule

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspbstuschedule.data.ScheduleRepositoryImpl
import com.example.myspbstuschedule.presentation.navigation.Routes
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: ScheduleRepositoryImpl,  // TODO убрать, добавить usecase
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<ScheduleState>(ScheduleState())
    val state = _state.asStateFlow()

    private val mode: String = checkNotNull(savedStateHandle[Routes.KEY_MODE])
    private val id: Int = checkNotNull(savedStateHandle[Routes.KEY_ID])

    private var currentDate: String = LocalDate.now().toString()

    init {
        get7DaysOfWeek()
    }

    fun getSchedule() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val schedule = withContext(Dispatchers.IO) {
                    when (mode) {
                        SearchMode.TEACHER.name -> {
                            repository.getTeachersSchedule(id, currentDate)
                        }

                        SearchMode.GROUP.name -> {
                            repository.getGroupsSchedule(id, currentDate)
                        }

                        else -> {
                            throw RuntimeException()
                        }
                    }
                }
                _state.value = _state.value.copy(
                    schedule = schedule,
                    isLoading = false
                )
                Log.d("Test", "getSchedule collected $schedule")
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Неизвестная ошибка",
                    schedule = null,
                    isLoading = false
                )
                Log.d("Test", "getSchedule caught ${e.message}")
            }
        }
    }

    fun get7DaysOfWeek(offset: Int = 0) {
        val date = LocalDate.now().plusWeeks(offset.toLong())
        currentDate = date.toString()
        val dayOfWeek = date.dayOfWeek.value
        val mondayDate = if (dayOfWeek != 1) date.minusDays((dayOfWeek - 1).toLong()) else date
        val dates = mutableListOf<LocalDate>().apply {
            repeat(7) {
                add(mondayDate.plusDays(it.toLong()))
            }
        }
        _state.value = _state.value.copy(weekDays = dates)
    }


    fun selectDay(dayOfWeek: Int) {
        _state.value = _state.value.copy(selectedDayOfWeek = dayOfWeek)
    }
}