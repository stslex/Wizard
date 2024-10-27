package com.stslex.wizard.feature.profile.ui.model

import com.stslex.wizard.feature.profile.domain.model.ProfileDomainModel

fun ProfileDomainModel.toUi(
    avatarModel: ProfileAvatarModel
) = ProfileModel(
    uuid = uuid,
    username = username,
    avatar = avatarModel,
    bio = bio,
    followers = followers,
    following = following,
    matches = matches,
    favouriteCount = favouriteCount,
    isFollowing = isFollowing,
    isFollowed = isFollowed,
    isCurrentUser = isCurrentUser
)