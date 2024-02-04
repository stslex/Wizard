package com.stslex.feature.favourite.ui.model

import com.stslex.feature.favourite.domain.model.FavouriteDomainModel

fun FavouriteDomainModel.toUI() = FavouriteModel(
    uuid = uuid,
    title = title,
    isFavourite = isFavourite
)

fun FavouriteModel.toDomain() = FavouriteDomainModel(
    uuid = uuid,
    title = title,
    isFavourite = isFavourite
)