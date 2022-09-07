package com.guoqiang.homepage.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.guoqiang.base.common.BaseViewModel
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.PageWrapperDto
import com.guoqiang.homepage.data.HomeRepository
import com.guoqiang.homepage.data.dto.CarCircle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: HomeRepository) : BaseViewModel() {

    val carCircleListLiveData: LiveData<BaseResponse<PageWrapperDto<CarCircle>>>
        get() = _carCircleListLiveData

    private var _carCircleListLiveData = MutableLiveData<BaseResponse<PageWrapperDto<CarCircle>>>()


    suspend fun getCarCircleList(): Flow<PagingData<CarCircle>> {
        return repo.getCarCircleList().cachedIn(viewModelScope)
    }


    fun test() {
        viewModelScope.launch {
            val response = repo.getCarCircleList(1, 10)
            _carCircleListLiveData.value = response
        }
    }



}
