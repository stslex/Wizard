package com.stslex.feature.profile.domain.model

data class ProfileDomainModel(
    val uuid: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val matches: Int,
    val favouriteCount: Int,
    val isFollowing: Boolean,
    val isFollowed: Boolean,
    val isCurrentUser: Boolean,
)
