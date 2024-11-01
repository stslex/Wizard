package com.stslex.wizard.feature.favourite.data.model

import com.stslex.wizard.core.network.clients.profile.model.response.UserFavouriteResultResponse

fun UserFavouriteResultResponse.toData() = FavouriteDataModel(
    uuid = uuid,
    title = title,
    isFavourite = isFavourite,
)