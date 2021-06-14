package com.alansoft.timetable.repository

import androidx.annotation.WorkerThread
import com.alansoft.timetable.BuildConfig
import com.alansoft.timetable.data.DataSource
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.request.TimeTableRequest
import com.alansoft.timetable.data.response.LecturesResponse
import com.alansoft.timetable.data.response.MsgResponse
import com.alansoft.timetable.data.response.TimeTableResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class ServiceRepository @Inject constructor(private val remote: DataSource) {
    @WorkerThread
    fun getLectures(
        onLoading: (Boolean) -> Unit
    ): Flow<Resource<LecturesResponse>> = flow {
        val response = remote.getLectures()
        if (response.items.isNullOrEmpty()) {
            emit(Resource.empty())
        } else {
            emit(Resource.success(response))
        }
    }.onStart {
        onLoading(true)
    }.retry(2) { cause ->
        cause is IOException
    }.catch { e ->
        emit(Resource.error(e))
    }.onCompletion {
        onLoading(false)
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getLecture(
        code: String,
        onLoading: (Boolean) -> Unit
    ): Flow<Resource<LecturesResponse>> = flow {
        val response = remote.getLecture(code)
        if (response.items.isNullOrEmpty()) {
            emit(Resource.empty())
        } else {
            emit(Resource.success(response))
        }
    }.onStart {
        onLoading(true)
    }.retry(2) { cause ->
        cause is IOException
    }.catch { e ->
        emit(Resource.error(e))
    }.onCompletion {
        onLoading(false)
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getTimeTable(
        onLoading: (Boolean) -> Unit
    ): Flow<Resource<TimeTableResponse>> = flow {
        val response = remote.getTimeTable()
        if (response.items.isNullOrEmpty()) {
            emit(Resource.empty())
        } else {
            emit(Resource.success(response))
        }
    }.onStart {
        onLoading(true)
    }.retry(2) { cause ->
        cause is IOException
    }.catch { e ->
        emit(Resource.error(e))
    }.onCompletion {
        onLoading(false)
    }.flowOn(Dispatchers.IO)

    fun postTimeTable(code: String): Flow<Resource<MsgResponse>> = flow {
        val request = TimeTableRequest(BuildConfig.Token, code)
        val response = remote.postTimeTable(request)
        if (response.message.isEmpty()) {
            emit(Resource.empty())
        } else {
            emit(Resource.success(response))
        }
    }.catch { e ->
        emit(Resource.error(e))
    }.flowOn(Dispatchers.IO)

}