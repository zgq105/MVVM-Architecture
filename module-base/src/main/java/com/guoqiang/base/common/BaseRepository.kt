package com.guoqiang.base.common

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.DataState
import com.guoqiang.base.network.PageWrapperDto
import com.guoqiang.base.network.StatusCode
import com.guoqiang.base.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


/**
 * @date：2021/5/20
 * @author fuusy
 * @instruction：子类Repository继承该类，网络请求时主要调用executeResp方法，
 *               具体流程请参考：https://juejin.cn/post/6961055228787425288
 */
open class BaseRepository {

    companion object {
        private const val TAG = "BaseRepository"
        private const val PAGE_SIZE = 25
    }

    /**
     * 方式二：结合Flow请求数据。
     * 根据Flow的不同请求状态，如onStart、onEmpty、onCompletion等设置baseResp.dataState状态值，
     * 最后通过stateLiveData分发给UI层。
     *
     * @param block api的请求方法
     */
    suspend fun <T : Any> executeReqWithFlow(
        block: suspend () -> BaseResponse<T>
    ): Flow<BaseResponse<T>> {
        var baseResp = BaseResponse<T>()
        return flow {
            val respResult = block.invoke()
            handleServerResponse(respResult)
            baseResp = respResult
            emit(respResult)
            LogUtil.debug(TAG, "executeReqWithFlow: $baseResp")
        }.flowOn(Dispatchers.IO).catch { e ->
            LogUtil.error(TAG, "executeReqWithFlow:code  ${baseResp.code}")
            baseResp.dataState = DataState.STATE_ERROR
            baseResp.error = e
            emit(baseResp)
        }
    }

    /**
     * 方式一
     * repo 请求数据的公共方法，
     * 在不同状态下先设置 baseResp.dataState的值，最后将dataState 的状态通知给UI
     * @param block api的请求方法
     * @return BaseResponse<T>
     */
    suspend fun <T : Any> executeResp(
        block: suspend () -> BaseResponse<T>
    ): BaseResponse<T> {
        var baseResp = BaseResponse<T>()
        try {
            baseResp.dataState = DataState.STATE_LOADING
            //开始请求数据
            val invoke = block.invoke()
            handleServerResponse(invoke)
            //将结果赋值给baseResp
            baseResp = invoke
        } catch (e: Exception) {
            //非后台返回错误，捕获到的异常
            baseResp.dataState = DataState.STATE_ERROR
            baseResp.error = e
        } finally {
            return baseResp
        }
    }


    private fun <T> isEmpty(baseResp: BaseResponse<T>): Boolean {
        if (baseResp.data == null || (baseResp.data is List<*> && (baseResp.data as List<*>).isEmpty())) {
            return true
        }

        return false
    }

    private fun <T> handleServerResponse(baseResp: BaseResponse<T>) {
        if (baseResp.code == StatusCode.CODE_SUCCESS.value) {
            //请求成功，判断数据是否为空
            if (isEmpty(baseResp)) {
                baseResp.dataState = DataState.STATE_EMPTY
            } else {
                baseResp.dataState = DataState.STATE_SUCCESS
            }
        } else {
            //服务器请求错误
            baseResp.dataState = DataState.STATE_FAILED
        }
    }

}