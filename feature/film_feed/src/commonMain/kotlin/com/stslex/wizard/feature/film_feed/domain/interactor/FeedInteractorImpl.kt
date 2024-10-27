package com.stslex.wizard.feature.film_feed.domain.interactor

import com.stslex.wizard.feature.film_feed.data.repository.FeedRepository
import com.stslex.wizard.feature.film_feed.domain.model.FeedDomainModel
import com.stslex.wizard.feature.film_feed.domain.model.toDomain

class FeedInteractorImpl(
    private val repository: FeedRepository
) : FeedInteractor {

    override suspend fun getFeed(
        page: Int,
        pageSize: Int
    ): FeedDomainModel = repository
        .getFeed(
            page = page,
            pageSize = pageSize
        ).toDomain()
}