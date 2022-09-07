package com.guoqiang.base.common.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.GsonBuilder
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.CommonException
import com.guoqiang.base.network.PageWrapperDto

/**
 * author: zgq
 * date: 2022/8/11 11:29 下午
 * destcription:
 */
abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>(), PagingApiCall<Value> {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val page = params.key ?: INIT_PAGE_INDEX
        return try {
            val response = loadData(page)
            Log.d("zgq", GsonBuilder().create().toJson(response))
            if (response.isSuccess) {
                if (response.data != null && response.data!!.list.isNotEmpty()) {
                    LoadResult.Page(
                        data = response.data!!.list,
                        prevKey = if (page == INIT_PAGE_INDEX) null else page - 1,
                        nextKey = if (page == response.data!!.totalPage) null else page + 1
                    )
                } else {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            } else {
                val exception = CommonException(response.code, response.message)
                LoadResult.Error(exception)
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object {
        const val INIT_PAGE_INDEX = 1
    }

}

interface PagingApiCall<T> {
    suspend fun  loadData(page: Int): BaseResponse<PageWrapperDto<T>>
}