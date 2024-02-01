package com.stslex.feature.favourite.data.repository

import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.feature.favourite.data.model.FavouriteDataModel
import com.stslex.feature.favourite.data.model.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavouriteRepositoryImpl(
    private val client: ProfileClient
) : FavouriteRepository {

    override fun getFavourites(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FavouriteDataModel>> = flow {
        val result = client
            .getFavourites(
                uuid = uuid,
                page = page,
                pageSize = pageSize
            )
            .result.map { result -> result.toData() }
        emit(result)
    }
}