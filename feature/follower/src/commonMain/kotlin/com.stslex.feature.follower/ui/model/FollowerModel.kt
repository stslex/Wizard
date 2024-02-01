package com.stslex.feature.follower.ui.model

import androidx.compose.runtime.Stable

@Stable
data class FollowerModel(
    val uuid: String,
    val username: String,
    val avatarUrl: String,
    val isFollowing: Boolean
)
