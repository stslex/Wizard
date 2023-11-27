package com.stslex.feature.feed.data.repository

import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.feature.feed.data.model.FeedDataModel
import com.stslex.feature.feed.data.model.toData

class FeedRepositoryImpl(
    private val client: FilmClient
) : FeedRepository {

    override suspend fun getFeed(
        page: Int,
        pageSize: Int
    ): FeedDataModel = client
        .getFeedFilms(
            page = page,
            pageSize = pageSize
        )
        .toData()
}

