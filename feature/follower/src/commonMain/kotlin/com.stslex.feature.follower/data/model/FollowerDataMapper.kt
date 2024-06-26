package com.stslex.feature.follower.data.model

import com.stslex.core.network.clients.profile.model.response.UserFollowerResponse

fun UserFollowerResponse.toData(): FollowerDataModel = FollowerDataModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    isFollowing = isFollowing
)
