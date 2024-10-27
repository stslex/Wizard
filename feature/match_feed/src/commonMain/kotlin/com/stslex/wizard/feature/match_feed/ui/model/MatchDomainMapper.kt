package com.stslex.wizard.feature.match_feed.ui.model

import com.stslex.wizard.feature.match_feed.domain.model.FilmDomain
import com.stslex.feature.match_feed.domain.model.MatchDomain
import com.stslex.feature.match_feed.domain.model.MatchFilmsPagingDomain
import com.stslex.feature.match_feed.domain.model.MatchParticipantDomain
import kotlinx.collections.immutable.toImmutableList

fun List<FilmDomain>.toUI(): List<FilmUi> = map { it.toUI() }

fun MatchDomain.toUI() = MatchUi(
    uuid = uuid,
    title = title,
    count = count,
    participant = participant.toUI(),
)

fun MatchParticipantDomain.toUI() = MatchParticipantUi(
    uuid = uuid,
    name = name,
    avatar = avatar,
)

fun MatchFilmsPagingDomain.toUI() = MatchFilmsPagingUi(
    films = films.map { it.toUI() }.toImmutableList(),
    hasNext = hasNext,
)

fun FilmDomain.toUI() = FilmUi(
    uuid = uuid,
    title = title,
    description = description,
    poster = poster,
    rate = rate,
    genres = genres.toImmutableList(),
)