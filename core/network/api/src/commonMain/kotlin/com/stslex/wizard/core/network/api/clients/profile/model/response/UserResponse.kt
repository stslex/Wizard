package com.stslex.wizard.core.network.api.clients.profile.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("username")
    val username: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("bio")
    val bio: String,
    @SerialName("followers_count")
    val followersCount: Int,
    @SerialName("following_count")
    val followingCount: Int,
    @SerialName("favourites_count")
    val favouritesCount: Int,
    @SerialName("matches_count")
    val matchesCount: Int,
    @SerialName("is_following")
    val isFollowing: Boolean,
    @SerialName("is_followed")
    val isFollowed: Boolean,
    @SerialName("is_current_user")
    val isCurrentUser: Boolean,
)
