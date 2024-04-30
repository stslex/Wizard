package com.stslex.feature.follower.ui.model

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingItem

@Stable
data class FollowerModel(
    override val uuid: String,
    val username: String,
    val avatarUrl: String,
    val isFollowing: Boolean
) : PagingItem
