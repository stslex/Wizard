package com.stslex.wizard.feature.match.domain.model

data class MatchUserDomainModel(
    val uuid: String,
    val avatar: String,
    val username: String,
    val isCreator: Boolean,
    val isAccepted: Boolean,
)