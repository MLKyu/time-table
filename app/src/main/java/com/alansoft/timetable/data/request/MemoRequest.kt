package com.alansoft.timetable.data.request

import com.google.gson.annotations.SerializedName

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */

/**
 * {
"user_key": "{사용자 ID 토큰}",
"code": "PG1807-50",
"type": "STUDY",
"title": "스터디",
"description": "스터디 시작",
"date": "2019-10-28"
 */
data class MemoRequest(
    @SerializedName("user_key")
    val userKey: String,
    val code: String,
    val type: String,
    val title: String?,
    val description: String?,
    val data: String?
)