package com.alansoft.timetable.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alansoft.timetable.R
import com.alansoft.timetable.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var adapter = LectureAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adapter = adapter
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_items, menu)
        setSearchMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun setSearchMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).run {
            queryHint = ""
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return true
                }
            })

            suggestionsAdapter

            // 검색 버튼 누르면 대박
            setOnSearchClickListener {
                viewModel.loadLecture()
            }

            setOnCloseListener {
                Log.d("asdfasdf", "asdfasdfasdf2")
                true
            }

            setOnClickListener {
                Log.d("asdfasdf", "asdfasdfasdf3")
            }
        }
    }

}