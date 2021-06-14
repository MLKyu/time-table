//package com.alansoft.timetable.ui.main
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.AsyncDifferConfig
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.alansoft.timetable.R
//import com.alansoft.timetable.data.response.LecturesItem
//
///**
// * Created by LEE MIN KYU on 2021/06/13
// * Copyright © 2021 Dreamus Company. All rights reserved.
// */
//class PageAdapter(private val itemCallback: ((LecturesItem) -> Unit)?) :
//    ListAdapter<LecturesItem, ViewHolder>(AsyncDifferConfig.Builder(DiffCallback()).build()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(parent)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.binding?.run {
////            setVariable(BR.item, item)
////
////            root.setOnClickListener {
////                val currentItem = getItem()
////                currentItem?.let {
////                    it.like = !it.like
////                    userLike.isSelected = it.like
////                    itemCallback?.invoke(it)
////                }
////            }
//        }
//    }
//}
//
//class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
//    LayoutInflater.from(parent.context).inflate(R.layout.item_lecture, parent, false)
//) {
//    var binding: ? = try {
//        DataBindingUtil.bind(itemView)
//    } catch (e: Exception) {
//        null
//    }
//}
//
//private class DiffCallback : DiffUtil.ItemCallback<LecturesItem>() {
//    override fun areItemsTheSame(oldItem: LecturesItem, newItem: LecturesItem): Boolean {
//        return oldItem.code == newItem.code && oldItem.lecture == newItem.lecture
//    }
//
//    override fun areContentsTheSame(oldItem: LecturesItem, newItem: LecturesItem): Boolean {
//        return oldItem == newItem
//    }
//}
