package com.stslex.feature.profile.data.model

import com.stslex.core.network.clients.profile.model.response.UserResponse

fun UserResponse.toData() = ProfileDataModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followersCount,
    following = followingCount,
    favouriteCount = favouritesCount,
    isFollowing = isFollowing,
    isFollowed = isFollowed,
    isCurrentUser = isCurrentUser
)