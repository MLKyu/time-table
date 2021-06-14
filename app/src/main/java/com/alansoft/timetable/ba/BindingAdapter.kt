package com.alansoft.timetable.ba

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.response.LecturesResponse
import com.alansoft.timetable.extension.loadWithThumbnail

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

//    @JvmStatic
//    @BindingAdapter("adapter")
//    fun bindAdapter(view: RecyclerView, adapter: PageAdapter) {
//        view.adapter = adapter
//    }
//
//    @JvmStatic
//    @BindingAdapter("lectures")
//    fun bindLectures(view: RecyclerView, response: Resource<LecturesResponse>?) {
//        when (response) {
//            is Resource.Success -> {
//                view.visibility = View.VISIBLE
//                (view.adapter as? PageAdapter)?.submitList(response.data.items)
//            }
//            is Resource.Empty -> {
//                view.visibility = View.GONE
//                (view.adapter as? PageAdapter)?.submitList(emptyList())
//            }
//            is Resource.Error -> {
//                view.visibility = View.GONE
//                view.context?.toast(response.exception.message.toString())
//            }
//        }
//    }


}