package com.guoqiang.base.network

/**
 * author: zgq
 * date: 2022/8/13 11:00 上午
 * destcription:
 */
data class CommonException(val code: Int, val errorMsg: String?) : Exception()