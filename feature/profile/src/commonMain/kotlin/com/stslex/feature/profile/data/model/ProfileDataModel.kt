package com.stslex.feature.profile.data.model

data class ProfileDataModel(
    val uuid: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val favouriteCount: Int,
)
