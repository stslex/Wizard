package com.stslex.wizard.feature.follower.ui.model

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.base.paging.PagingItem

@Stable
data class FollowerModel(
    override val uuid: String,
    val username: String,
    val avatarUrl: String,
    val isFollowing: Boolean
) : PagingItem
