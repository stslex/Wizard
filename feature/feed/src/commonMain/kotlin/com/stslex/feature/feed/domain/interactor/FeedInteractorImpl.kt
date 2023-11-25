package com.stslex.feature.feed.domain.interactor

import com.stslex.feature.feed.data.repository.FeedRepository
import com.stslex.feature.feed.domain.model.FeedDomainModel
import com.stslex.feature.feed.domain.model.toDomain

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