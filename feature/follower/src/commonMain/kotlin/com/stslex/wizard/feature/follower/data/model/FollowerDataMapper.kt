package com.stslex.wizard.feature.follower.data.model

import com.stslex.wizard.core.network.api.clients.profile.model.response.UserFollowerResponse

fun UserFollowerResponse.toData(): FollowerDataModel = FollowerDataModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    isFollowing = isFollowing
)
