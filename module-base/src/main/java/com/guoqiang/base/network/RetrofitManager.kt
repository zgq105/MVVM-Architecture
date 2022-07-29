package com.guoqiang.base.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: zgq
 * date: 2022/7/26 9:11 下午
 * destcription:
 */
object RetrofitManager {

    private  var baseUrl = ""

    fun init(baseUrl: String) {
        this.baseUrl = baseUrl
    }

    private val retrofitMap = hashMapOf<String, Retrofit>()


    fun <T> create(service: Class<T>, baseUrl: String? = null): T {
        return getRetrofit(service, baseUrl).create(service)
    }

    private fun <T> getRetrofit(service: Class<T>, baseUrl: String? = null): Retrofit {
        val url = baseUrl ?: this.baseUrl

        val retrofitKey = url + service.name
        if (retrofitMap.containsKey(retrofitKey)) {
            val retrofit = retrofitMap[retrofitKey]
            if (retrofit != null) return retrofit
        }

        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitMap[retrofitKey] = retrofit
        return retrofit
    }
}