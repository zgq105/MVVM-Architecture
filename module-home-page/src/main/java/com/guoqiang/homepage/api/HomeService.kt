package com.guoqiang.homepage.api

import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.PageWrapperDto
import com.guoqiang.base.network.RetrofitManager
import com.guoqiang.homepage.data.dto.CarCircle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * author: zgq
 * date: 2022/7/25 8:24 下午
 * destcription:
 */
interface HomeService {

    @GET("car_circle/list")
    suspend fun getCarCircleList(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): BaseResponse<PageWrapperDto<CarCircle>>


    companion object {
        private const val BASE_URL = ""

        fun create(): HomeService {
            return RetrofitManager.create(HomeService::class.java)
        }
    }

}