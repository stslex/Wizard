package com.stslex.feature.favourite.domain.model

import com.stslex.feature.favourite.data.model.FavouriteDataModel

fun FavouriteDataModel.toDomain() = FavouriteDomainModel(
    uuid = uuid,
    title = title,
    isFavourite = isFavourite,
)