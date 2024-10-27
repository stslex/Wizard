package com.stslex.wizard.feature.match.data.model

data class MatchUserDataModel(
    val uuid: String,
    val avatar: String,
    val username: String,
    val isCreator: Boolean,
    val isAccepted: Boolean,
)