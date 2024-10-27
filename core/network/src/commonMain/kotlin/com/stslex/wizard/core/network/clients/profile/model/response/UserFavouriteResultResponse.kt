package com.stslex.wizard.core.network.clients.profile.model.response

import com.stslex.wizard.core.core.paging.PagingCoreItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFavouriteResultResponse(
    @SerialName("uuid")
    override val uuid: String,
    @SerialName("title")
    val title: String,
    @SerialName("is_favourite")
    val isFavourite: Boolean,
) : PagingCoreItem