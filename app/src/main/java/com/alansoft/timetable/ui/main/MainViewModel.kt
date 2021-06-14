package com.alansoft.timetable.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.response.LecturesResponse
import com.alansoft.timetable.repository.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ServiceRepository
) : ViewModel() {
    private var isLoading = MutableLiveData<Boolean>()
    var _results: MutableLiveData<Resource<LecturesResponse>> = MutableLiveData()
    val results: LiveData<Resource<LecturesResponse>> = _results
    var job: Job? = null

    fun loadLecture() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.getLectures()
                .debounce(350)
                .collect {
                    _results.postValue(it)
                }
        }
    }


}