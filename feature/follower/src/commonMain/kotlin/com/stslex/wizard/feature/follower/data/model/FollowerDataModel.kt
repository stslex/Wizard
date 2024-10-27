package com.stslex.wizard.feature.follower.data.model

import com.stslex.wizard.core.core.paging.PagingCoreItem

data class FollowerDataModel(
    override val uuid: String,
    val username: String,
    val avatarUrl: String,
    val isFollowing: Boolean
) : PagingCoreItem
