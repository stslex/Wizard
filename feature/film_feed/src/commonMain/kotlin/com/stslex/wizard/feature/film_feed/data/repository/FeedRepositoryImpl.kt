package com.stslex.wizard.feature.film_feed.data.repository

import com.stslex.wizard.core.network.api.clients.film.client.FilmClient
import com.stslex.wizard.feature.film_feed.data.model.FeedDataModel
import com.stslex.wizard.feature.film_feed.data.model.toData
import org.koin.core.annotation.Factory

@Factory
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

