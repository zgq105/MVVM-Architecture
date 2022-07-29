package com.guoqiang.user.data.dto

/**
 * author: zgq
 * date: 2022/7/25 8:30 下午
 * destcription:
 */

data class LoginEntity(
    val admin: Boolean,
    val coinCount: Int,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)