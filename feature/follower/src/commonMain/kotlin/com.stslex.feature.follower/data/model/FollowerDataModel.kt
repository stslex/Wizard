package com.stslex.feature.follower.data.model

import com.stslex.core.core.paging.PagingCoreItem

data class FollowerDataModel(
    override val uuid: String,
    val username: String,
    val avatarUrl: String,
    val isFollowing: Boolean
) : PagingCoreItem
