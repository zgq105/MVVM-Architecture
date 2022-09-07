package com.guoqiang.homepage.data.dto

import com.google.gson.annotations.SerializedName

/**
 * author: zgq
 * date: 2022/8/26 4:34 下午
 * destcription:
 */
data class CarCircle(
    @SerializedName("car_circle_id")
    val carCircleId: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("active_rider")
    val activeRider: Int, //活跃车友
    @SerializedName("certified_car_owner")
    val certifiedCarOwner: Int, //认证车主
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("image_list")
    val imageList: List<String>,
    @SerializedName("ranking")
    val ranking: Int, //排名
    @SerializedName("heat")
    val heat: Int, //热度
    @SerializedName("share_count")
    val shareCount: Int, //分享
    @SerializedName("comment_count")
    val commentCount: Int, //评论
    @SerializedName("like_count")
    val likeCount: Int, //点赞

)