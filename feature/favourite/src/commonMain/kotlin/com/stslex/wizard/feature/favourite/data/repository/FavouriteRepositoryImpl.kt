package com.stslex.wizard.feature.favourite.data.repository

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.core.paging.pagingMap
import com.stslex.wizard.core.network.api.clients.profile.client.ProfileClient
import com.stslex.wizard.core.network.api.clients.profile.model.request.PagingProfileRequest
import com.stslex.wizard.feature.favourite.data.model.FavouriteDataModel
import com.stslex.wizard.feature.favourite.data.model.toData
import com.stslex.wizard.feature.favourite.di.FavouriteScope
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FavouriteScope::class)
@Scoped
class FavouriteRepositoryImpl(
    private val client: ProfileClient
) : FavouriteRepository {

    override suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FavouriteDataModel> = client
        .getFavourites(
            PagingProfileRequest(
                uuid = uuid,
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
        .pagingMap { result ->
            result.toData()
        }

    override suspend fun addFavourite(model: FavouriteDataModel) {
        client.addFavourite(
            uuid = model.uuid,
            title = model.title
        )
    }

    override suspend fun removeFavourite(uuid: String) {
        client.removeFavourite(uuid)
    }
}