package com.guoqiang

import android.app.Application
import com.guoqiang.business.common.ARouterConfig

/**
 * author: zgq
 * date: 2022/7/19 5:57 下午
 * destcription:
 */
class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        ARouterConfig.init(this)
    }
}