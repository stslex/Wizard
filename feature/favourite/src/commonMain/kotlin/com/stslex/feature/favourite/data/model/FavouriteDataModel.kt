package com.stslex.feature.favourite.data.model

import com.stslex.core.core.paging.PagingCoreItem

data class FavouriteDataModel(
    override val uuid: String,
    val title: String,
    val isFavourite: Boolean,
) : PagingCoreItem
