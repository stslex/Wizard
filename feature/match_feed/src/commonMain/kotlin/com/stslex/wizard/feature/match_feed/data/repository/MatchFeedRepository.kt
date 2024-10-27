package com.stslex.wizard.feature.match_feed.data.repository

import com.stslex.feature.match_feed.data.model.MatchData
import com.stslex.feature.match_feed.data.model.MatchFilmsPagingData
import com.stslex.feature.match_feed.data.model.MatchPagingData
import kotlinx.coroutines.flow.Flow

interface MatchFeedRepository {

    fun getMatch(uuid: String): Flow<MatchData>

    fun getLatestMatch(): Flow<MatchData>

    suspend fun getMatchFilms(
        matchUuid: String,
        page: Int,
        pageSize: Int
    ): MatchFilmsPagingData

    suspend fun getMatches(): MatchPagingData
}