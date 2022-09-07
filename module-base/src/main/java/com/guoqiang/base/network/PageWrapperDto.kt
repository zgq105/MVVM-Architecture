package com.guoqiang.base.network

import com.google.gson.annotations.SerializedName

/**
 * author: zgq
 * date: 2022/8/27 3:51 下午
 * destcription:
 */
data class PageWrapperDto<T>(
    @SerializedName("page")
    val page: Int = 1, //当前页
    @SerializedName("totalPage")
    val totalPage: Int, //总页数
    @SerializedName("size")
    val size: Int = 10, //每页大小
    @SerializedName("hasPrev")
    val hasPrev: Boolean = false,//是否有上一页
    @SerializedName("hasNext")
    val hasNext: Boolean = false,//是否有下一页
    @SerializedName("total")
    val total: Int,//总数
    @SerializedName("list")
    val list: List<T> //业务数据列表
)