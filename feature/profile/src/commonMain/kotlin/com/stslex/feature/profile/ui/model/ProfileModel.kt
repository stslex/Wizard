package com.stslex.feature.profile.ui.model

import androidx.compose.runtime.Stable

@Stable
data class ProfileModel(
    val uuid: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val favouriteCount: Int,
)
