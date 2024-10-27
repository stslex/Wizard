package com.stslex.wizard.feature.match_feed.data.repository

import com.stslex.wizard.core.network.clients.film.client.FilmClient
import com.stslex.wizard.feature.match_feed.data.model.MatchData
import com.stslex.wizard.feature.match_feed.data.model.MatchFilmsPagingData
import com.stslex.wizard.feature.match_feed.data.model.MatchPagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MatchFeedRepositoryImpl(
    private val client: FilmClient
) : MatchFeedRepository {

    override fun getMatch(uuid: String): Flow<MatchData> {
        TODO()
    }

    override fun getLatestMatch(): Flow<MatchData> = flow {
        TODO()
    }

    override suspend fun getMatchFilms(
        matchUuid: String,
        page: Int, pageSize: Int
    ): MatchFilmsPagingData {
        TODO()
    }

    override suspend fun getMatches(): MatchPagingData {
        TODO()
    }
}