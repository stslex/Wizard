package com.stslex.feature.favourite.data.repository

import com.stslex.feature.favourite.data.model.FavouriteDataModel

interface FavouriteRepository {

    suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): List<FavouriteDataModel>

    suspend fun addFavourite(model: FavouriteDataModel)

    suspend fun removeFavourite(uuid: String)
}
