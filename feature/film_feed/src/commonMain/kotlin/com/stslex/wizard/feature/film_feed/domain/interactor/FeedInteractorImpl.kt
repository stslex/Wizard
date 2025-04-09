package com.stslex.wizard.feature.film_feed.domain.interactor

import com.stslex.wizard.feature.film_feed.data.repository.FeedRepository
import com.stslex.wizard.feature.film_feed.di.FilmFeedScope
import com.stslex.wizard.feature.film_feed.domain.model.FeedDomainModel
import com.stslex.wizard.feature.film_feed.domain.model.toDomain
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FilmFeedScope::class)
@Scoped
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