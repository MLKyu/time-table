package com.alansoft.timetable.repository

import androidx.annotation.WorkerThread
import com.alansoft.timetable.data.DataSource
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.response.LecturesResponse
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
    fun getLectures(): Flow<Resource<LecturesResponse>> = flow {
        val response = remote.getLectures()
        if (response.items.isNullOrEmpty()) {
            emit(Resource.empty())
        } else {
            emit(Resource.success(response))
        }
    }.onStart {
    }.retry(2) { cause ->
        cause is IOException
    }.catch { e ->
        emit(Resource.error(e))
    }.onCompletion {
    }.flowOn(Dispatchers.IO)
}