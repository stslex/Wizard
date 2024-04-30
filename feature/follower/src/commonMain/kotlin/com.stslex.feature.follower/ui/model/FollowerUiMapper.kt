package com.stslex.feature.follower.ui.model

import com.stslex.feature.follower.data.model.FollowerDataModel

fun FollowerDataModel.toUi(): FollowerModel = FollowerModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    isFollowing = isFollowing
)