package com.guoqiang

import android.app.Application
import com.guoqiang.base.network.RetrofitManager
import com.guoqiang.business.common.ARouterConfig
import dagger.hilt.android.HiltAndroidApp

/**
 * author: zgq
 * date: 2022/7/19 5:57 下午
 * destcription:
 */
@HiltAndroidApp
class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        ARouterConfig.init(this)
        RetrofitManager.init("https://mock.apifox.cn/m1/1379127-0-default/")
    }
}