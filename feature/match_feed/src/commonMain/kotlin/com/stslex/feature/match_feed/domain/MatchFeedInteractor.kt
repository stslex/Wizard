package com.stslex.feature.match_feed.domain

import com.stslex.feature.match_feed.domain.model.MatchDomain
import com.stslex.feature.match_feed.domain.model.MatchFilmsPagingDomain
import kotlinx.coroutines.flow.Flow

interface MatchFeedInteractor {

    fun getLatestMatch(): Flow<MatchDomain>

    suspend fun getMatchFilms(
        matchUuid: String,
        page: Int,
        pageSize: Int
    ): MatchFilmsPagingDomain
}