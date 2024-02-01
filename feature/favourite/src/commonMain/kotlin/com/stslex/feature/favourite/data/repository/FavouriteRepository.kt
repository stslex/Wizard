package com.stslex.feature.favourite.data.repository

import com.stslex.feature.favourite.data.model.FavouriteDataModel
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    fun getFavourites(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FavouriteDataModel>>
}
