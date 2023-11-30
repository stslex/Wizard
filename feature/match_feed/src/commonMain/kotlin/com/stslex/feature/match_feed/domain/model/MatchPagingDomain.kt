package com.stslex.feature.match_feed.domain.model

data class MatchPagingDomain(
    val matches: List<MatchDomain>,
    val hasNext: Boolean,
)
