package com.stslex.feature.profile.domain.model

data class ProfileDomainModel(
    val uuid: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val favouriteCount: Int,
)
