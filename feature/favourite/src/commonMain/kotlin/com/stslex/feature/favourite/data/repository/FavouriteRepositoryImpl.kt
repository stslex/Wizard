package com.stslex.feature.favourite.data.repository

import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.core.network.clients.profile.model.request.PagingRequest
import com.stslex.feature.favourite.data.model.FavouriteDataModel
import com.stslex.feature.favourite.data.model.toData

class FavouriteRepositoryImpl(
    private val client: ProfileClient
) : FavouriteRepository {

    override suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): List<FavouriteDataModel> = client
        .getFavourites(
            PagingRequest(
                uuid = uuid,
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
        .result
        .map { result ->
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