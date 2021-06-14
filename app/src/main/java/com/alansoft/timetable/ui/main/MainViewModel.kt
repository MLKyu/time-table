package com.alansoft.timetable.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.response.LecturesResponse
import com.alansoft.timetable.data.response.TimeTableResponse
import com.alansoft.timetable.extension.dayMapping
import com.alansoft.timetable.extension.timeMapping
import com.alansoft.timetable.repository.ServiceRepository
import com.alansoft.timetableview.Schedule
import com.alansoft.timetableview.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ServiceRepository
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    private val _results: MutableLiveData<Resource<LecturesResponse>> = MutableLiveData()
    val results: LiveData<Resource<LecturesResponse>> = _results

    val lectureResult: MutableLiveData<Resource<LecturesResponse>> = MutableLiveData()
    val timeTableResult: MutableLiveData<Resource<List<Schedule>>> = MutableLiveData()

    var job: Job? = null

    fun loadLecture() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.getLectures(onLoading = { isLoading.postValue(it) })
                .debounce(350)
                .collect {
                    Log.d("asdfasdfasdfasf", "asdfasdfasdf")
                    _results.postValue(it)
                }
        }
    }

    fun loadLecture(cdoe: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.getLecture(cdoe,
                onLoading = { isLoading.postValue(it) })
                .debounce(350)
                .collect {
                    lectureResult.postValue(it)
                }
        }
    }

    fun loadTimeTable() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.getTimeTable { isLoading.postValue(it) }
                .debounce(350)
                .zip(repository.getLectures { isLoading.postValue(it) }) { a, b ->
                    merge(a, b)
                }.collect {
                    timeTableResult.postValue(it)
                }
        }
    }

    private fun merge(
        timeTable: Resource<TimeTableResponse>,
        lectures: Resource<LecturesResponse>
    ): Resource<List<Schedule>> {
        val schedules = mutableListOf<Schedule>()
        if (timeTable is Resource.Success && lectures is Resource.Success) {

            if (timeTable.data.items.isNullOrEmpty() || lectures.data.items.isNullOrEmpty()) {
                return Resource.empty()
            } else {
                val tables = timeTable.data.items.toList()
                val lecs = lectures.data.items.toList()
                tables.forEach { ti ->
                    val schedule = lecs.find { it.code == ti.lectureCode }
                    schedule?.let { lecItem ->
                        lecItem.dayOfWeek?.forEach { dayof ->
                            schedules.add(Schedule().apply {
                                classTitle = lecItem.lecture
                                classPlace = lecItem.location
                                day = dayof.dayMapping()
                                professorName = lecItem.professor
                                startTime = lecItem.startTime?.timeMapping() ?: Time()
                                endTime = lecItem.endTime?.timeMapping() ?: Time()
                            })
                        }
                    }
                }

                return Resource.success(schedules)
            }
        } else {
            return Resource.error(Throwable("data error"))
        }
    }


}