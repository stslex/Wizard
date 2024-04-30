package com.stslex.feature.favourite.domain.model

import com.stslex.core.core.paging.PagingCoreItem

data class FavouriteDomainModel(
    override val uuid: String,
    val title: String,
    val isFavourite: Boolean,
) : PagingCoreItem
