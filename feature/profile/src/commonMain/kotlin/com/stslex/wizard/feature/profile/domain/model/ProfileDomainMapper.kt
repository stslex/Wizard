package com.stslex.wizard.feature.profile.domain.model

import com.stslex.wizard.feature.profile.data.model.ProfileDataModel

fun ProfileDataModel.toDomain() = ProfileDomainModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followers,
    following = following,
    matches = matches,
    favouriteCount = favouriteCount,
    isFollowing = isFollowing,
    isFollowed = isFollowed,
    isCurrentUser = isCurrentUser
)