package com.stslex.wizard.feature.favourite.domain.interactor

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.core.paging.pagingMap
import com.stslex.wizard.feature.favourite.data.repository.FavouriteRepository
import com.stslex.wizard.feature.favourite.domain.model.FavouriteDomainModel
import com.stslex.wizard.feature.favourite.domain.model.toData
import com.stslex.wizard.feature.favourite.domain.model.toDomain

class FavouriteInteractorImpl(
    private val repository: FavouriteRepository
) : FavouriteInteractor {

    override suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FavouriteDomainModel> = repository
        .getFavourites(
            uuid = uuid,
            query = query,
            page = page,
            pageSize = pageSize
        ).pagingMap { favourite ->
            favourite.toDomain()
        }

    override suspend fun setFavourite(model: FavouriteDomainModel) {
        if (model.isFavourite) {
            repository.addFavourite(model.toData())
        } else {
            repository.removeFavourite(model.uuid)
        }
    }
}