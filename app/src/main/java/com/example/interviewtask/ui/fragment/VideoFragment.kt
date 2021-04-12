package com.example.interviewtask.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewtask.data.MyApi
import com.example.interviewtask.databinding.VideosFragmentBinding
import com.example.interviewtask.ui.adapter.VideoAdapter
import com.example.interviewtask.ui.adapter.FeatureLoadStateAdapter
import com.example.interviewtask.ui.viewmodel.FeatureViewModel
import com.example.interviewtask.ui.FeatureViewModelFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


class VideoFragment : Fragment() {

    private lateinit var viewModel: FeatureViewModel
    private lateinit var binding: VideosFragmentBinding
    val videoAdapter = VideoAdapter()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = VideosFragmentBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = FeatureViewModelFactory(MyApi())
        viewModel = ViewModelProvider(this, factory).get(FeatureViewModel::class.java)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = videoAdapter.withLoadStateHeaderAndFooter(
                    header = FeatureLoadStateAdapter { videoAdapter.retry() },
                    footer = FeatureLoadStateAdapter { videoAdapter.retry() })
        }

        videoAdapter.notifyDataSetChanged()
            lifecycleScope.launch {
                    viewModel.feature.collectLatest { pagedData ->
                        videoAdapter.submitData(pagedData)
                    }

            }
    }

    override fun onPause() {
        super.onPause()
        binding == null
    }

}



