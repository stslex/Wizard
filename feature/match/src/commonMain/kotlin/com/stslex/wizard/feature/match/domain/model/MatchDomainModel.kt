package com.stslex.wizard.feature.match.domain.model

import com.stslex.wizard.core.ui.kit.base.paging.PagingItem

data class MatchDomainModel(
    override val uuid: String,
    val title: String,
    val description: String,
    val status: MatchDomainStatus,
    val participants: List<MatchUserDomainModel>,
    val isCreator: Boolean,
    val expiresAtDays: Int,
    val expiresAtHours: Int,
    val expiresAtMinutes: Int,
    val expiresAtSeconds: Int,
) : PagingItem
