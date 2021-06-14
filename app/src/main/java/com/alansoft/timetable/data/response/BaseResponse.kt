package com.alansoft.timetable.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
open class BaseResponse<T> {
    @SerializedName("Items")
    var items: List<T> = listOf()

    @SerializedName("Count")
    var count: Long = -1

    @SerializedName("ScannedCount")
    var scannedCount: Long = -1
}