package com.guoqiang.business.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * author: zgq
 * date: 2022/7/19 5:02 下午
 * destcription:
 */
class ARouterConfig private constructor(){

    companion object {
        fun init(application: Application) {
            if (!EnvironmentConfig.isProEnvironment()) {
                ARouter.openLog()
                ARouter.openDebug()
            }
            ARouter.init(application)
        }
    }
}