package com.guoqiang.base.network

import com.google.gson.annotations.SerializedName

/**
 * json返回的基本类型
 */
class BaseResponse<T>{
    @SerializedName("code")
    var code = -1
    @SerializedName("msg")
    var msg: String? = null
    @SerializedName("data")
    var data: T? = null

    //下面是app业务侧字段
    var dataState: DataState = DataState.STATE_UNKNOWN
    var error: Throwable? = null

    val isSuccess: Boolean
        get() = code == StatusCode.CODE_SUCCESS.value
}

enum class DataState {
    STATE_CREATE,//创建
    STATE_LOADING,//加载中
    STATE_SUCCESS,//成功
    STATE_COMPLETED,//完成
    STATE_EMPTY,//数据为null
    STATE_FAILED,//接口请求成功但是服务器返回error
    STATE_ERROR,//请求失败
    STATE_UNKNOWN//未知
}

enum class StatusCode(val value: Int) {
    CODE_SUCCESS(0)
}