package com.stslex.feature.profile.ui.model

import com.stslex.feature.profile.domain.model.ProfileDomainModel

fun ProfileDomainModel.toUi() = ProfileModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followers,
    following = following,
    favouriteCount = favouriteCount,
)