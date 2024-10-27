package com.stslex.wizard.feature.favourite.domain.model

import com.stslex.wizard.feature.favourite.data.model.FavouriteDataModel

fun FavouriteDataModel.toDomain() = FavouriteDomainModel(
    uuid = uuid,
    title = title,
    isFavourite = isFavourite,
)

fun FavouriteDomainModel.toData() = FavouriteDataModel(
    uuid = uuid,
    title = title,
    isFavourite = isFavourite,
)