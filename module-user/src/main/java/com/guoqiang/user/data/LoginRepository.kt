package com.guoqiang.user.data

import com.guoqiang.base.common.BaseRepository
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.user.api.UserService
import com.guoqiang.user.data.dto.LoginEntity
import javax.inject.Inject


class LoginRepository @Inject constructor(private val service: UserService) : BaseRepository() {

    suspend fun login(userName: String, password: String): BaseResponse<LoginEntity> {
        return executeResp { service.login(userName, password) }
    }

    suspend fun register(
        userName: String,
        password: String,
        rePassword: String
    ): BaseResponse<LoginEntity> {
        return executeResp { service.register(userName, password, rePassword) }
    }

}