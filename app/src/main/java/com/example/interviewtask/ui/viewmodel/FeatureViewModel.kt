package com.example.interviewtask.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.interviewtask.data.MyApi
import com.example.interviewtask.repository.DataRepository
import kotlinx.coroutines.flow.cache


class FeatureViewModel(
        private val api: MyApi,
) : ViewModel() {
    var feature = Pager(PagingConfig(pageSize = 10)) {
        DataRepository(api)
    }.flow.cachedIn(viewModelScope)
}