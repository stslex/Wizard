package com.stslex.feature.profile.ui.model

import com.stslex.feature.profile.domain.model.ProfileDomainModel

fun ProfileDomainModel.toUi(
    avatarModel: ProfileAvatarModel
) = ProfileModel(
    uuid = uuid,
    username = username,
    avatar = avatarModel,
    bio = bio,
    followers = followers,
    following = following,
    favouriteCount = favouriteCount,
    isFollowing = isFollowing,
    isFollowed = isFollowed,
    isCurrentUser = isCurrentUser
)