package com.stslex.wizard.feature.match.ui.model

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

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
) : PagingItem {

    companion object {

        val EMPTY = MatchUiModel(
            uuid = "",
            title = "",
            description = "",
            status = MatchUiStatusModel.PENDING,
            participants = persistentListOf(),
            isCreator = false,
            expiresAtDays = 0,
            expiresAtHours = 0,
            expiresAtMinutes = 0,
        )
    }
}
