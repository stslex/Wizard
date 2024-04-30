package com.stslex.feature.favourite.domain.interactor

import com.stslex.core.core.paging.PagingResponse
import com.stslex.feature.favourite.domain.model.FavouriteDomainModel

interface FavouriteInteractor {

    suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FavouriteDomainModel>

    suspend fun setFavourite(model: FavouriteDomainModel)
}
