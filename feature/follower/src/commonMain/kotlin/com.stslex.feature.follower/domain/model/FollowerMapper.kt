package com.stslex.feature.follower.domain.model

import com.stslex.feature.follower.data.model.FollowerDataModel
import com.stslex.feature.follower.ui.model.FollowerModel

fun List<FollowerDataModel>.toUI(): List<FollowerModel> = map { it.toUI() }

fun FollowerDataModel.toUI(): FollowerModel = FollowerModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    isFollowing = isFollowing
)