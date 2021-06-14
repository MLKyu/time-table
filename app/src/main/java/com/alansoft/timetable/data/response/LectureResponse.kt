package com.alansoft.timetable.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
/**
 *{
"Items": [
{
"code": "PG1807-01",
"lecture": "World Wide English 1",
"professor": "Caitlyn Lee",
"location": "혜인관403",
"start_time": "10:45",
"end_time": "12:30",
"dayofweek": [
"금",
"월"
]
}
],
"Count": 1,
"ScannedCount": 1
}
 */
class LecturesResponse : BaseResponse<LecturesItem>()

@Parcelize
data class LecturesItem(
    val code: String?,
    val lecture: String?,
    val professor: String?,
    val location: String?,
    @SerializedName("start_time")
    val startTime: String?,
    @SerializedName("end_time")
    val endTime: String?,
    @SerializedName("dayofweek")
    val dayOfWeek: List<String>?
) : Parcelable
