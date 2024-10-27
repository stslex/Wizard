package com.stslex.wizard.feature.match.data.model

import com.stslex.wizard.core.core.asyncMap
import com.stslex.wizard.core.network.clients.match.model.response.MatchResponse
import com.stslex.wizard.core.network.clients.match.model.response.MatchStatusResponse
import com.stslex.wizard.core.network.clients.match.model.response.MatchUserResponse

internal suspend fun MatchResponse.toData(
    userUUID: String
) = MatchDataModel(
    uuid = uuid,
    title = title,
    description = description,
    status = status.toData(),
    participants = participants.asyncMap { it.toData() },
    isCreator = userUUID == creatorUuid,
    expiresAt = expiresAt,
)

private fun MatchStatusResponse.toData() = when (this) {
    MatchStatusResponse.PENDING -> MatchDataStatusModel.PENDING
    MatchStatusResponse.ACTIVE -> MatchDataStatusModel.ACTIVE
    MatchStatusResponse.EXPIRED -> MatchDataStatusModel.EXPIRED
    MatchStatusResponse.COMPLETED -> MatchDataStatusModel.COMPLETED
    MatchStatusResponse.CANCELED -> MatchDataStatusModel.CANCELED
}

private fun MatchUserResponse.toData() = MatchUserDataModel(
    uuid = uuid,
    avatar = avatar,
    username = username,
    isCreator = isCreator,
    isAccepted = isAccepted
)