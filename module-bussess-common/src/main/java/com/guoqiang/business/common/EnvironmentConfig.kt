package com.guoqiang.business.common


/**
 * author: zgq
 * date: 2022/7/19 5:08 下午
 * destcription:
 */
object EnvironmentConfig {

    private var environmentEnum: EnvironmentEnum = EnvironmentEnum.DEV

    fun setEnvironment(environmentEnum: EnvironmentEnum) {
        this.environmentEnum = environmentEnum
    }

    fun getEnvironment(): EnvironmentEnum {
        return environmentEnum
    }

    fun isProEnvironment() = getEnvironment() == EnvironmentEnum.PROD

    enum class EnvironmentEnum {
        DEV,
        UAT,
        PROD
    }
}