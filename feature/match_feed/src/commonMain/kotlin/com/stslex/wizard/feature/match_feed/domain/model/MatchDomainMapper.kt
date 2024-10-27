package com.stslex.wizard.feature.match_feed.domain.model

import com.stslex.feature.match_feed.data.model.FilmData
import com.stslex.feature.match_feed.data.model.MatchData
import com.stslex.feature.match_feed.data.model.MatchFilmsPagingData
import com.stslex.feature.match_feed.data.model.MatchParticipantData

fun MatchData.toDomain() = MatchDomain(
    uuid = uuid,
    title = title,
    count = count,
    participant = participant.toDomain(),
)

fun MatchParticipantData.toDomain() = MatchParticipantDomain(
    uuid = uuid,
    name = name,
    avatar = avatar,
)

fun MatchFilmsPagingData.toDomain() = MatchFilmsPagingDomain(
    films = films.map { it.toDomain() },
    hasNext = hasNext,
)

fun FilmData.toDomain() = com.stslex.wizard.feature.match_feed.domain.model.FilmDomain(
    uuid = uuid,
    title = title,
    description = description,
    poster = poster,
    rate = rate,
    genres = genres,
)