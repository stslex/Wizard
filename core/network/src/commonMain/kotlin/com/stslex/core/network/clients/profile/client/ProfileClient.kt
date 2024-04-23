package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.clients.profile.model.request.PagingProfileRequest
import com.stslex.core.network.clients.profile.model.response.BooleanResponse
import com.stslex.core.network.clients.profile.model.response.UserFavouriteResultResponse
import com.stslex.core.network.clients.profile.model.response.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.response.UserResponse
import com.stslex.core.network.clients.profile.model.response.UserSearchResponse

interface ProfileClient {

    suspend fun getProfile(uuid: String): UserResponse

    suspend fun getProfile(): UserResponse

    suspend fun searchUser(request: PagingProfileRequest): UserSearchResponse

    suspend fun getFavourites(request: PagingProfileRequest): PagingResponse<UserFavouriteResultResponse>

    suspend fun getFollowers(request: PagingProfileRequest): UserFollowerResponse

    suspend fun getFollowing(request: PagingProfileRequest): UserFollowerResponse

    suspend fun addFavourite(
        uuid: String,
        title: String,
    )

    suspend fun isFavourite(favouriteUuid: String): BooleanResponse

    suspend fun removeFavourite(uuid: String)
}