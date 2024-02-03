package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.clients.profile.model.response.UserFavouriteResponse
import com.stslex.core.network.clients.profile.model.response.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.response.UserResponse
import com.stslex.core.network.clients.profile.model.response.UserSearchResponse

interface ProfileClient {

    suspend fun getProfile(uuid: String): UserResponse

    suspend fun getProfile(): UserResponse

    suspend fun searchUser(
        query: String,
        page: Int,
        pageSize: Int
    ): UserSearchResponse

    suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): UserFavouriteResponse

    suspend fun getFollowers(
        uuid: String,
        page: Int,
        pageSize: Int
    ): UserFollowerResponse

    suspend fun getFollowing(
        uuid: String,
        page: Int,
        pageSize: Int
    ): UserFollowerResponse

    suspend fun addFavourite(
        uuid: String,
        title: String,
    )

    suspend fun removeFavourite(uuid: String)
}