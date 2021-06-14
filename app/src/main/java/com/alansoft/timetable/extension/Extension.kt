package com.alansoft.timetable.extension

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.alansoft.timetableview.Time
import java.text.ParseException

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
fun ImageView.loadWithThumbnail(uri: String?) {
//    Glide.with(context)
//        .load(uri)
//        .thumbnail(0.1f)
//        .transition(DrawableTransitionOptions.withCrossFade())
//        .into(this)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun String.dayMapping(): Int {
    return when (this) {
        "월" -> 0
        "화" -> 1
        "수" -> 2
        "목" -> 3
//        "금" -> 4
        else -> 4
    }
}

fun String.timeMapping(): Time {
    return try {
        val splitTime: List<String> = this.split(":")
        val hours = splitTime[0]
        val minutes = splitTime[1]
        println("$hours hours and $minutes minutes")
        Time(hours.toInt(), minutes.toInt())
    } catch (e: ParseException) {
        Time()
    }
}