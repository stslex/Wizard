package com.stslex.wizard.feature.favourite.ui.model

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.base.paging.PagingItem

@Stable
data class FavouriteModel(
    override val uuid: String,
    val title: String,
    val isFavourite: Boolean,
) : PagingItem