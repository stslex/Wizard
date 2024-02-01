package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.clients.profile.model.ProfileResponse
import com.stslex.core.network.clients.profile.model.UserFavouriteResponse
import com.stslex.core.network.clients.profile.model.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.UserSearchResponse

interface ProfileClient {

    suspend fun getProfile(uuid: String): ProfileResponse

    suspend fun getProfile(): ProfileResponse

    suspend fun searchUser(
        query: String,
        page: Int,
        pageSize: Int
    ): UserSearchResponse

    suspend fun getFavourites(
        uuid: String,
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
}