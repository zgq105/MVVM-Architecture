package com.guoqiang.user.api

import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.RetrofitManager
import com.guoqiang.user.data.dto.LoginEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: zgq
 * date: 2022/7/25 8:24 下午
 * destcription:
 */
interface UserService {
    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(@Field("username") username: String, @Field("password") password: String
                         , @Field("repassword") repassword: String) : BaseResponse<LoginEntity>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): BaseResponse<LoginEntity>


    companion object {
        private const val BASE_URL = ""

        fun create(): UserService {
            return RetrofitManager.create(UserService::class.java)
        }
    }

}