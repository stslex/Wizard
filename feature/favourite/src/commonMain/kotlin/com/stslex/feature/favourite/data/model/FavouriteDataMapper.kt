package com.stslex.feature.favourite.data.model

import com.stslex.core.network.clients.profile.model.UserFavouriteResultResponse

fun UserFavouriteResultResponse.toData() = FavouriteDataModel(
    uuid = uuid,
    title = title,
    isFavourite = isFavourite,
)