package com.alansoft.timetable.data

import com.alansoft.timetable.BuildConfig
import com.alansoft.timetable.data.api.ServiceApi
import com.alansoft.timetable.data.request.TimeTableRequest
import com.alansoft.timetable.data.response.LecturesResponse
import com.alansoft.timetable.data.response.MsgResponse
import com.alansoft.timetable.data.response.TimeTableResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class DataSource @Inject constructor(private val serviceApi: ServiceApi) {
    suspend fun getLectures(): LecturesResponse {
        return serviceApi.lectures()
    }

    suspend fun getLecture(code: String): LecturesResponse {
        return serviceApi.lecture(code)
    }

    suspend fun getTimeTable(): TimeTableResponse {
        return serviceApi.timetable(BuildConfig.Token)
    }

    suspend fun postTimeTable(request: TimeTableRequest):MsgResponse {
        return serviceApi.insertTimetable(request)
    }
}