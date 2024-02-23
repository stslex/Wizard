package com.stslex.feature.profile.ui.model

import androidx.compose.runtime.Stable

@Stable
data class ProfileModel(
    val uuid: String,
    val username: String,
    val avatar: ProfileAvatarModel,
    val bio: String,
    val followers: Int,
    val following: Int,
    val favouriteCount: Int,
    val isFollowing: Boolean,
    val isFollowed: Boolean,
    val isCurrentUser: Boolean,
)
