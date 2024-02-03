package com.stslex.feature.favourite.domain.interactor

import com.stslex.feature.favourite.data.repository.FavouriteRepository
import com.stslex.feature.favourite.domain.model.FavouriteDomainModel
import com.stslex.feature.favourite.domain.model.toData
import com.stslex.feature.favourite.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteInteractorImpl(
    private val repository: FavouriteRepository
) : FavouriteInteractor {

    override fun getFavourites(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FavouriteDomainModel>> = repository
        .getFavourites(
            uuid = uuid,
            page = page,
            pageSize = pageSize
        )
        .map { favourites ->
            favourites.map { favourite -> favourite.toDomain() }
        }

    override suspend fun setFavourite(model: FavouriteDomainModel) {
        if (model.isFavourite) {
            repository.addFavourite(model.toData())
        } else {
            repository.removeFavourite(model.uuid)
        }
    }
}