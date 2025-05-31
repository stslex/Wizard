package com.stslex.wizard.feature.match_feed.domain

import com.stslex.wizard.feature.match_feed.data.repository.MatchFeedRepository
import com.stslex.wizard.feature.match_feed.di.MatchDetailsScope
import com.stslex.wizard.feature.match_feed.domain.model.MatchDomain
import com.stslex.wizard.feature.match_feed.domain.model.MatchFilmsPagingDomain
import com.stslex.wizard.feature.match_feed.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchDetailsScope::class)
@Scoped
internal class MatchFeedInteractorImpl(
    private val repository: MatchFeedRepository
) : MatchFeedInteractor {

    override fun getLatestMatch(): Flow<MatchDomain> = repository
        .getLatestMatch()
        .map { it.toDomain() }

    override suspend fun getMatchFilms(
        matchUuid: String,
        page: Int,
        pageSize: Int
    ): MatchFilmsPagingDomain = repository
        .getMatchFilms(
            matchUuid = matchUuid,
            page = page,
            pageSize = pageSize
        )
        .toDomain()
}
