package com.stslex.feature.profile.data.model

import com.stslex.core.network.clients.profile.model.ProfileResponse

fun ProfileResponse.toData() = ProfileDataModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followers,
    following = following,
    favouriteCount = favouriteCount,
)