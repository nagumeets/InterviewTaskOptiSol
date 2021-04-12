package com.example.interviewtask.repository

import androidx.paging.PagingSource
import com.example.interviewtask.data.MyApi
import com.example.interviewtask.data.models.FeatureX

private const val STARTING_PAGE_INDEX = 1

class DataRepository(
        private val api: MyApi,
) : PagingSource<Int, FeatureX>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeatureX> {
        return try {
            val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
            val proximity: String = "45.521728,-122.67326"
            val response = api.getData(proximity, 100)
            LoadResult.Page(
                    data = response.features,
                    prevKey = if (nextPageNumber > STARTING_PAGE_INDEX) nextPageNumber - 1 else null,
                    nextKey = if (nextPageNumber < 300) nextPageNumber + 1 else null
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}