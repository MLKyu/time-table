package com.alansoft.timetable.data

import com.alansoft.timetable.data.api.ServiceApi
import com.alansoft.timetable.data.response.LecturesResponse
import javax.inject.Inject

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class DataSource @Inject constructor(private val serviceApi: ServiceApi) {
    suspend fun getLectures(): LecturesResponse {
        return serviceApi.lectures()
    }
}