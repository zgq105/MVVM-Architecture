package com.guoqiang.user

import android.app.Application
import com.guoqiang.base.network.RetrofitManager
import dagger.hilt.android.HiltAndroidApp

/**
 * author: zgq
 * date: 2022/7/29 7:07 下午
 * destcription:
 */
@HiltAndroidApp
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        RetrofitManager.init("https://mock.apifox.cn/m1/1379127-0-default/")
    }
}