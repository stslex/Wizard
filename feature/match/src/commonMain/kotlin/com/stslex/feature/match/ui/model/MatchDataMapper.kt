package com.stslex.feature.match.ui.model

import com.stslex.core.core.asyncMap
import com.stslex.core.ui.pager.PagerLoadState
import com.stslex.feature.match.domain.model.MatchDomainModel
import com.stslex.feature.match.domain.model.MatchDomainStatus
import com.stslex.feature.match.domain.model.MatchUserDomainModel
import com.stslex.feature.match.ui.store.MatchScreenState
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

fun PagerLoadState.toUi() = when (this) {
    PagerLoadState.Data -> MatchScreenState.Content.Data
    PagerLoadState.Empty -> MatchScreenState.Empty
    PagerLoadState.Initial -> MatchScreenState.Shimmer
    PagerLoadState.Loading -> MatchScreenState.Content.Append
    PagerLoadState.Refresh -> MatchScreenState.Content.Refresh
    PagerLoadState.Retry -> MatchScreenState.Shimmer
    is PagerLoadState.Error -> MatchScreenState.Error(error)
}