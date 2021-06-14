package com.alansoft.timetable.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alansoft.timetable.R
import com.alansoft.timetable.data.Resource
import com.alansoft.timetable.data.response.LecturesItem
import com.alansoft.timetable.databinding.MainFragmentBinding
import com.alansoft.timetable.extension.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private var adapter = LectureAdapter(this::onItemClicked)

    private var searchView: SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adapter = adapter
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("asdfasdf", item.toString() + "          " + searchView?.imeOptions)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_items, menu)
        setSearchMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTimeTable()
        setHasOptionsMenu(true)
        setSubscribe()
    }

    private fun recyclerViewVisible(visibility: Boolean) {
        binding.recyclerView.visibility = if (!visibility) View.GONE else View.VISIBLE
    }

    private fun setSearchMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        searchView = (searchItem.actionView as SearchView).apply {
            queryHint = ""
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return true
                }
            })


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

            setOnQueryTextFocusChangeListener { p0, p1 ->
                if (p0 is SearchView && p1) {
                    recyclerViewVisible(p1)
                } else if (p0 is SearchView && !p1) {
                    recyclerViewVisible(p1)
                }
            }
        }
    }

    private fun setSubscribe() {
        viewModel.lectureResult.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    showLecture(it.data.items[0])
                }
                is Resource.Empty -> {
                    context?.toast("검색 결과가 없습니다.")
                }
                is Resource.Error -> {
                    if (it.isNetworkError) {
                        context?.toast("네트워크 연결을 해주세요")
                    } else {
                        context?.toast("Error ${it.exception}")
                    }
                }
            }
        })
    }

    private fun onItemClicked(item: LecturesItem) {
        item.code?.let {
            if (findNavController().currentDestination?.id == R.id.mainFragment) {
                viewModel.loadLecture(it)
            }
        }
    }

    private fun showLecture(item: LecturesItem) {
        if (findNavController().currentDestination?.id == R.id.mainFragment) {
            val direction = MainFragmentDirections.searchToDialog(0, item)
            findNavController().navigate(direction)
        }
    }
}