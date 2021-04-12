package com.example.interviewtask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtask.data.MyApi
import com.example.interviewtask.ui.viewmodel.FeatureViewModel

@Suppress("UNCHECKED_CAST")
class FeatureViewModelFactory(
    private val api: MyApi,
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeatureViewModel(api) as T
    }
}