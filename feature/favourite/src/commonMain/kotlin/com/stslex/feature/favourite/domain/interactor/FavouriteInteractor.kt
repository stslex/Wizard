package com.stslex.feature.favourite.domain.interactor

import com.stslex.feature.favourite.domain.model.FavouriteDomainModel
import kotlinx.coroutines.flow.SharedFlow

interface FavouriteInteractor {

    val favourites: SharedFlow<List<FavouriteDomainModel>>

    suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    )

    suspend fun setFavourite(model: FavouriteDomainModel)
}
