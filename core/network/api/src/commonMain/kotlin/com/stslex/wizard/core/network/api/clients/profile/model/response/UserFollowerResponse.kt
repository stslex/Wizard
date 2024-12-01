package com.stslex.wizard.core.network.api.clients.profile.model.response

import com.stslex.wizard.core.core.paging.PagingCoreItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFollowerResponse(
    @SerialName("uuid")
    override val uuid: String,
    @SerialName("username")
    val username: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("is_following")
    val isFollowing: Boolean
) : PagingCoreItem