package com.alansoft.timetable.ba

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.response.LecturesResponse
import com.alansoft.timetable.extension.loadWithThumbnail
import com.alansoft.timetable.extension.toast
import com.alansoft.timetable.ui.main.LectureAdapter
import com.alansoft.timetableview.Schedule
import com.alansoft.timetableview.TimetableView

/**
 * Created by LEE MIN KYU on 2021/06/13
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImg")
    fun bindLaodImg(view: ImageView, url: String) {
        view.loadWithThumbnail(url)
    }

    @JvmStatic
    @BindingAdapter("lectureAdapter")
    fun bindLectureAdapter(view: RecyclerView, adapter: LectureAdapter) {
        view.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, response: Resource<LecturesResponse>?) {
        when (response) {
            is Resource.Success -> {
                view.visibility = View.VISIBLE
                (view.adapter as? LectureAdapter)?.run {
                    submitList(response.data.items)
                }
            }
            is Resource.Empty -> {
                view.visibility = View.GONE
                (view.adapter as? LectureAdapter)?.submitList(emptyList())
            }
            is Resource.Error -> {
                view.visibility = View.GONE
                view.context?.toast(response.exception.message.toString())
            }
        }
    }

    @JvmStatic
    @BindingAdapter("timeTable")
    fun bindTimeTable(view: TimetableView, response: Resource<List<Schedule>>?) {
        when (response) {
            is Resource.Success -> {
                view.add(response.data)
            }
            is Resource.Empty -> {
                view.context?.toast("response.exception.message.toString()")
            }
            is Resource.Error -> {
                view.context?.toast(response.exception.message.toString())
            }
        }
    }
}