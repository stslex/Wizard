package com.stslex.feature.match_feed.domain.model

data class MatchDomain(
    val uuid: String,
    val title: String,
    val participant: MatchParticipantDomain,
    val count: Int,
)
