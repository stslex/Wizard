package com.stslex.wizard.feature.profile.data.model

import com.stslex.wizard.core.network.clients.profile.model.response.UserResponse

fun UserResponse.toData() = ProfileDataModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followersCount,
    following = followingCount,
    matches = matchesCount,
    favouriteCount = favouritesCount,
    isFollowing = isFollowing,
    isFollowed = isFollowed,
    isCurrentUser = isCurrentUser,
)