package com.stslex.feature.favourite.domain.interactor

import com.stslex.core.network.utils.PagingWorker
import com.stslex.feature.favourite.data.repository.FavouriteRepository
import com.stslex.feature.favourite.domain.model.FavouriteDomainModel
import com.stslex.feature.favourite.domain.model.toData
import com.stslex.feature.favourite.domain.model.toDomain
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class FavouriteInteractorImpl(
    private val pagingWorker: PagingWorker,
    private val repository: FavouriteRepository
) : FavouriteInteractor {

    private val _favourites = MutableSharedFlow<List<FavouriteDomainModel>>()
    override val favourites: SharedFlow<List<FavouriteDomainModel>> = _favourites.asSharedFlow()

    override suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ) {
        pagingWorker {
            val items = repository
                .getFavourites(
                    uuid = uuid,
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
                .map { favourite ->
                    favourite.toDomain()
                }
            val screenItems = favourites
                .replayCache
                .lastOrNull()
                .orEmpty()
                .plus(items)
            _favourites.emit(screenItems)
        }
    }

    override suspend fun setFavourite(model: FavouriteDomainModel) {
        if (model.isFavourite) {
            repository.addFavourite(model.toData())
        } else {
            repository.removeFavourite(model.uuid)
        }
    }
}