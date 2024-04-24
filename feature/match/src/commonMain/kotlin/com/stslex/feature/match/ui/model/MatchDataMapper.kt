package com.stslex.feature.match.ui.model

import com.stslex.core.core.asyncMap
import com.stslex.feature.match.domain.model.MatchDomainModel
import com.stslex.feature.match.domain.model.MatchDomainStatus
import com.stslex.feature.match.domain.model.MatchUserDomainModel
import kotlinx.collections.immutable.toImmutableList

internal suspend fun MatchDomainModel.toUi() = MatchUiModel(
    uuid = uuid,
    title = title,
    description = description,
    status = status.toUi(),
    participants = participants
        .asyncMap { it.toUi() }
        .toImmutableList(),
    isCreator = isCreator,
    expiresAtDays = expiresAtDays,
    expiresAtHours = expiresAtHours,
    expiresAtMinutes = expiresAtMinutes,
)

private fun MatchDomainStatus.toUi() = when (this) {
    MatchDomainStatus.PENDING -> MatchUiStatusModel.PENDING
    MatchDomainStatus.ACTIVE -> MatchUiStatusModel.ACTIVE
    MatchDomainStatus.EXPIRED -> MatchUiStatusModel.EXPIRED
    MatchDomainStatus.COMPLETED -> MatchUiStatusModel.COMPLETED
    MatchDomainStatus.CANCELED -> MatchUiStatusModel.CANCELED
}

private fun MatchUserDomainModel.toUi() = MatchUserUiModel(
    uuid = uuid,
    avatar = avatar,
    username = username,
    isCreator = isCreator,
    isAccepted = isAccepted
)