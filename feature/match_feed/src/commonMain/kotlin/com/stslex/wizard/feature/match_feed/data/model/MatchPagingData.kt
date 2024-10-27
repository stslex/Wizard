package com.stslex.wizard.feature.match_feed.data.model

data class MatchPagingData(
    val matches: List<MatchData>,
    val hasNext: Boolean,
)
