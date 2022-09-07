package com.guoqiang.homepage.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.guoqiang.base.common.BaseRepository
import com.guoqiang.base.common.paging.BasePagingSource
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.PageWrapperDto
import com.guoqiang.homepage.api.HomeService
import com.guoqiang.homepage.data.dto.CarCircle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeRepository @Inject constructor(private val service: HomeService) : BaseRepository() {

    suspend fun getCarCircleList(
        page: Int,
        size: Int = 10
    ): BaseResponse<PageWrapperDto<CarCircle>> {
        return executeResp { service.getCarCircleList(page, size) }
    }


    suspend fun getCarCircleList(): Flow<PagingData<CarCircle>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE)
        ) {
            CarCirclePagingSource(service, PAGE_SIZE)
        }.flow
    }

    companion object{
        private const val PAGE_SIZE = 10
    }

}