package com.stslex.wizard.feature.favourite.data.repository

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.core.paging.pagingMap
import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.core.network.clients.profile.model.request.PagingProfileRequest
import com.stslex.wizard.feature.favourite.data.model.FavouriteDataModel
import com.stslex.wizard.feature.favourite.data.model.toData

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