

package com.guoqiang.homepage.data

import com.guoqiang.base.common.paging.BasePagingSource
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.PageWrapperDto
import com.guoqiang.homepage.api.HomeService
import com.guoqiang.homepage.data.dto.CarCircle

class CarCirclePagingSource(private val service: HomeService, private val pageSize:Int): BasePagingSource<CarCircle>() {
    override suspend fun loadData(page: Int): BaseResponse<PageWrapperDto<CarCircle>> {
        return service.getCarCircleList(page,pageSize)
    }
}
