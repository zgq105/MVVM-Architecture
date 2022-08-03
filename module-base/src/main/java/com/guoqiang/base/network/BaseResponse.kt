package com.guoqiang.base.network

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.guoqiang.base.R

/**
 * json返回的基本类型
 */
class BaseResponse<T>{
    @SerializedName("code")
    var code = -1
    @SerializedName("message")
    var message: String? = ""
    @SerializedName("data")
    var data: T? = null

    //下面是app业务侧字段
    var dataState: DataState = DataState.STATE_UNKNOWN
    var error: Throwable? = null

    val isSuccess: Boolean
        get() = code == StatusCode.CODE_SUCCESS.value

    fun getErrorMsg(context: Context): String {
        return when (this.dataState) {
            DataState.STATE_EMPTY -> context.getString(R.string.base_data_null)
            DataState.STATE_FAILED -> {//接口请求成功但是服务器返回error code
                getErrorTip(context)
            }
            DataState.STATE_ERROR -> context.getString(R.string.base_network_error)
            else -> context.getString(R.string.base_unknown_error)
        }
    }

    private fun getErrorTip(context: Context): String {
        return when (code) {
            StatusCode.CODE_BAD_REQUEST.value -> context.getString(R.string.base_bad_request)
            StatusCode.CODE_UNAUTHORIZED.value -> context.getString(R.string.base_unauthorized)
            StatusCode.CODE_FORBIDDEN.value -> context.getString(R.string.base_forbidden)
            StatusCode.CODE_NOT_FOUND.value -> context.getString(R.string.base_not_found)
            StatusCode.CODE_INTERNAL_SERVER_ERROR.value -> context.getString(R.string.base_internal_server_error)
            StatusCode.CODE_NOT_IMPLEMENTED.value -> context.getString(R.string.base_not_implemented)
            StatusCode.CODE_BAD_GATEWAY.value -> context.getString(R.string.base_bad_gateway)
            StatusCode.CODE_SERVICE_UNAVAILABLE.value -> context.getString(R.string.base_service_unavailable)
            else -> context.getString(R.string.base_unknown_error)
        }
    }
}

enum class DataState {
    STATE_CREATE,//创建
    STATE_LOADING,//加载中
    STATE_SUCCESS,//成功
    STATE_COMPLETED,//完成
    STATE_EMPTY,//数据为null
    STATE_FAILED,//接口请求成功但是服务器返回error code
    STATE_ERROR,//请求失败
    STATE_UNKNOWN//未知
}

enum class StatusCode(val value: Int) {
    CODE_SUCCESS(200),
    CODE_BAD_REQUEST(400),
    CODE_UNAUTHORIZED(401),
    CODE_FORBIDDEN(403),
    CODE_NOT_FOUND(404),
    CODE_INTERNAL_SERVER_ERROR(500),
    CODE_NOT_IMPLEMENTED(501),
    CODE_BAD_GATEWAY(502),
    CODE_SERVICE_UNAVAILABLE(503),
}