package com.stslex.feature.match.domain.model

import com.stslex.core.core.asyncMap
import com.stslex.feature.match.data.model.MatchDataModel
import com.stslex.feature.match.data.model.MatchDataStatusModel
import com.stslex.feature.match.data.model.MatchUserDataModel

suspend fun MatchDataModel.toDomain() = MatchDomainModel(
    uuid = uuid,
    title = title,
    description = description,
    status = status.toDomain(),
    participants = participants.asyncMap { it.toDomain() },
    isCreator = isCreator,
    expiresAtDays = expiresAt.toDays(),
    expiresAtHours = expiresAt.toHours(),
    expiresAtMinutes = expiresAt.toMinutes(),
    expiresAtSeconds = expiresAt.toSeconds(),
)

private fun Long.toDays() = (this / (24 * 60 * 60)).toInt()

private fun Long.toHours() = ((this % (24 * 60 * 60)) / (60 * 60)).toInt()

private fun Long.toMinutes() = ((this % (60 * 60)) / 60).toInt()

private fun Long.toSeconds() = (this % 60).toInt()

private fun MatchUserDataModel.toDomain() = MatchUserDomainModel(
    uuid = uuid,
    avatar = avatar,
    username = username,
    isCreator = isCreator,
    isAccepted = isAccepted
)

private fun MatchDataStatusModel.toDomain() = when (this) {
    MatchDataStatusModel.PENDING -> MatchDomainStatus.PENDING
    MatchDataStatusModel.ACTIVE -> MatchDomainStatus.ACTIVE
    MatchDataStatusModel.EXPIRED -> MatchDomainStatus.EXPIRED
    MatchDataStatusModel.COMPLETED -> MatchDomainStatus.COMPLETED
    MatchDataStatusModel.CANCELED -> MatchDomainStatus.CANCELED
}