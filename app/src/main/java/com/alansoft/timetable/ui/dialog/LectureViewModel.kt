package com.alansoft.timetable.ui.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.response.MsgResponse
import com.alansoft.timetable.repository.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by LEE MIN KYU on 2021/06/14
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
@HiltViewModel
class LectureViewModel @Inject constructor(
    private val repository: ServiceRepository
) : ViewModel() {
    private var _results: MutableLiveData<Resource<MsgResponse>> = MutableLiveData()
    val results: LiveData<Resource<MsgResponse>> = _results
    var job: Job? = null

    fun insertTimeTable(code: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.postTimeTable(code)
                .debounce(350)
                .collect {
                    _results.postValue(it)
                }
        }
    }
}