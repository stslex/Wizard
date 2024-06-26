package com.stslex.feature.match.data.model

import com.stslex.core.ui.base.paging.PagingItem

data class MatchDataModel(
    override val uuid: String,
    val title: String,
    val description: String,
    val status: MatchDataStatusModel,
    val participants: List<MatchUserDataModel>,
    val isCreator: Boolean,
    val expiresAt: Long,
) : PagingItem
