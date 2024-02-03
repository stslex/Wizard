package com.stslex.feature.favourite.domain.interactor

import com.stslex.core.network.utils.currentTimeMs
import com.stslex.feature.favourite.data.repository.FavouriteRepository
import com.stslex.feature.favourite.domain.model.FavouriteDomainModel
import com.stslex.feature.favourite.domain.model.toData
import com.stslex.feature.favourite.domain.model.toDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class FavouriteInteractorImpl(
    private val repository: FavouriteRepository
) : FavouriteInteractor {

    private val _favourites = MutableSharedFlow<List<FavouriteDomainModel>>()
    override val favourites: SharedFlow<List<FavouriteDomainModel>> = _favourites.asSharedFlow()

    private var favouritesJob: Job? = null
    private var favouritesNextPageJob: Job? = null
    private var lastRequestTime = 0L

    override suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ) {
        if (lastRequestTime + REQUEST_DELAY > currentTimeMs) {
            favouritesNextPageJob = getFavouritesJob(
                uuid = uuid,
                query = query,
                page = page,
                pageSize = pageSize,
                start = CoroutineStart.LAZY
            )
        }
        favouritesJob = getFavouritesJob(
            uuid = uuid,
            query = query,
            page = page,
            pageSize = pageSize
        )
    }

    private suspend fun getFavouritesJob(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int,
        start: CoroutineStart = CoroutineStart.DEFAULT
    ): Job = CoroutineScope(coroutineContext)
        .launch(
            start = start
        ) {
            favouritesJob = favouritesNextPageJob
            favouritesNextPageJob = null
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
        }.apply {
            invokeOnCompletion {
                favouritesNextPageJob?.start()
            }
        }

    override suspend fun setFavourite(model: FavouriteDomainModel) {
        if (model.isFavourite) {
            repository.addFavourite(model.toData())
        } else {
            repository.removeFavourite(model.uuid)
        }
    }

    companion object {
        private const val REQUEST_DELAY = 500L
    }
}