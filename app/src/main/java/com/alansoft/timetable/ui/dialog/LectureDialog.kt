package com.alansoft.timetable.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.alansoft.timetable.R
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.databinding.FragmentLectureBinding
import com.alansoft.timetable.extension.toast
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by LEE MIN KYU on 2021/06/14
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
@AndroidEntryPoint
class LectureDialog : DialogFragment() {
    private val args: LectureDialogArgs by navArgs()
    lateinit var binding: FragmentLectureBinding
    val viewModel by viewModels<LectureViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lecture, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        setSubscribe()
    }

    private fun bindView() {
        with(binding) {
            setVariable(BR.item, args.item)

            when (args.type) {
                0 -> {
                    dialogBtn.text = "강의 추가"
                }
                1 -> {
                    dialogBtn.text = "메모 추가"
                }
            }
            dialogBtn.setOnClickListener {
                args.item.code?.let { it1 ->
                    viewModel.insertTimeTable(it1)
                }
            }
        }
    }

    private fun setSubscribe() {
        viewModel.results.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    context?.toast(it.data.message)
                    dismiss()
                }
                is Resource.Empty -> {
                    context?.toast("")
                }
                is Resource.Error -> {
                    var msg = it.exception.toString()
                    if (it.exception.message?.contains(409.toString()) == true) {
                        msg = "이미 등록한 강의입니다."
                    }
                    context?.toast(msg)
                }
            }
        })
    }
}