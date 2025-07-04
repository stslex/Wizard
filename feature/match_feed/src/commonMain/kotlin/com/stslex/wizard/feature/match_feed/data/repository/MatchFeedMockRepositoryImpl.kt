package com.stslex.wizard.feature.match_feed.data.repository

import com.stslex.wizard.core.network.api.clients.film.client.FilmClient
import com.stslex.wizard.feature.match_feed.data.model.FilmData
import com.stslex.wizard.feature.match_feed.data.model.MatchData
import com.stslex.wizard.feature.match_feed.data.model.MatchFilmsPagingData
import com.stslex.wizard.feature.match_feed.data.model.MatchPagingData
import com.stslex.wizard.feature.match_feed.data.model.MatchParticipantData
import com.stslex.wizard.feature.match_feed.di.MatchDetailsScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchDetailsScope::class)
@Scoped
internal class MatchFeedMockRepositoryImpl(
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
                    description = "", // TODO refactor this
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