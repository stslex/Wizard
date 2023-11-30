package com.stslex.feature.match_feed.data.repository

import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.feature.match_feed.data.model.FilmData
import com.stslex.feature.match_feed.data.model.MatchData
import com.stslex.feature.match_feed.data.model.MatchFilmsPagingData
import com.stslex.feature.match_feed.data.model.MatchPagingData
import com.stslex.feature.match_feed.data.model.MatchParticipantData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MatchFeedMockRepositoryImpl(
    private val client: FilmClient
) : MatchFeedRepository {

    private val matchData = MatchData(
        uuid = "uuid",
        title = "title",
        participant = MatchParticipantData(
            uuid = "uuid",
            name = "name",
            avatar = "avatar"
        ),
        count = 1,
    )

    override fun getMatch(uuid: String): Flow<MatchData> {
        TODO("Not yet implemented")
    }

    override fun getLatestMatch(): Flow<MatchData> = flow {
        delay(1000)
        emit(matchData)
    }

    override suspend fun getMatchFilms(
        matchUuid: String,
        page: Int,
        pageSize: Int
    ): MatchFilmsPagingData {
        delay(1000)
        val randomFilms = client.getFeedFilms(page, pageSize)
        return MatchFilmsPagingData(
            films = randomFilms.results.map {
                FilmData(
                    uuid = it.id,
                    title = it.title,
                    description = it.description,
                    poster = it.poster,
                    genres = it.genres,
                    rate = it.rating,
                )
            },
            randomFilms.hasNext
        )
    }

    override suspend fun getMatches(): MatchPagingData {
        TODO("Not yet implemented")
    }
}