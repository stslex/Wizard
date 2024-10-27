package com.stslex.wizard.feature.match_feed.data.model

data class MatchData(
    val uuid: String,
    val title: String,
    val participant: MatchParticipantData,
    val count: Int,
)