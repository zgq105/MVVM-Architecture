package com.guoqiang.user.data.dto

import com.google.gson.annotations.SerializedName

/**
 * author: zgq
 * date: 2022/7/25 8:30 下午
 * destcription:
 */

data class LoginEntity(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("avatar_url")
    val avatarUrl:String
)