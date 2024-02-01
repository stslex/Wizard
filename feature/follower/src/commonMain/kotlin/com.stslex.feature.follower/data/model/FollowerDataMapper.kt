package com.stslex.feature.follower.data.model

import com.stslex.core.network.clients.profile.model.UserFollowerResponse

fun UserFollowerResponse.toData(): List<FollowerDataModel> = this
    .result
    .map { response ->
        FollowerDataModel(
            uuid = response.uuid,
            username = response.username,
            avatarUrl = response.avatarUrl,
            isFollowing = response.isFollowing
        )
    }