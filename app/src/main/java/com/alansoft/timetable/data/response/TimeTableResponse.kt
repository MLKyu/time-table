package com.alansoft.timetable.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
/**
 * {
"Items": [
{
"lecture_code": "PG1807-10"
}
],
"Count": 1,
"ScannedCount": 1
}
 */
class TimeTableResponse : BaseResponse<TimeTableItem>()

data class TimeTableItem(
    @SerializedName("lecture_code")
    val lectureCode: String
)