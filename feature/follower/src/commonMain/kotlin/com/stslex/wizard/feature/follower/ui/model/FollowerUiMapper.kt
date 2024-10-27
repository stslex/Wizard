package com.stslex.wizard.feature.follower.ui.model

import com.stslex.wizard.feature.follower.data.model.FollowerDataModel

fun FollowerDataModel.toUi(): FollowerModel = FollowerModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    isFollowing = isFollowing
)