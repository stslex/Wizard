package com.stslex.feature.match.ui.model

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingItem
import kotlinx.collections.immutable.ImmutableList

@Stable
data class MatchUiModel(
    override val uuid: String,
    val title: String,
    val description: String,
    val status: MatchUiStatusModel,
    val participants: ImmutableList<MatchUserUiModel>,
    val isCreator: Boolean,
    val expiresAtDays: Int,
    val expiresAtHours: Int,
    val expiresAtMinutes: Int,
) : PagingItem
