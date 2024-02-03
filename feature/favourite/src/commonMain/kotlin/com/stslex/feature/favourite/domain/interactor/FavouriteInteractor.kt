package com.stslex.feature.favourite.domain.interactor

import com.stslex.feature.favourite.domain.model.FavouriteDomainModel
import kotlinx.coroutines.flow.Flow

interface FavouriteInteractor {

    fun getFavourites(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FavouriteDomainModel>>

    suspend fun setFavourite(model: FavouriteDomainModel)
}

