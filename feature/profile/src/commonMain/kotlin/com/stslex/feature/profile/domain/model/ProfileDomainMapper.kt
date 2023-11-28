package com.stslex.feature.profile.domain.model

import com.stslex.feature.profile.data.model.ProfileDataModel

fun ProfileDataModel.toDomain() = ProfileDomainModel(
    uuid = uuid,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followers,
    following = following,
    favouriteCount = favouriteCount,
)