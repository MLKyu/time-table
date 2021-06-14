package com.alansoft.timetable.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class MemoResponse : BaseResponse<MemoItem>()

/**
 * "user_key": "token_key_grepp",
"lecture_code": "PG1807-01",
"type": "EXAM",
"title": "일정 추가하기",
"description": "중간 고사",
"date": "2019-10-28",
 */
data class MemoItem(
    @SerializedName("user_key")
    val userKey: String?,
    @SerializedName("lecture_code")
    val lectureCode: String?,
    val type: String?,
    val description: String?,
    val date: String?
)