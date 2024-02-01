package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.api.server.client.ServerApiClient
import com.stslex.core.network.clients.profile.model.UserResponse
import com.stslex.core.network.clients.profile.model.UserFavouriteResponse
import com.stslex.core.network.clients.profile.model.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.UserSearchResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ProfileClientImpl(
    private val client: ServerApiClient
) : ProfileClient {

    override suspend fun getProfile(
        uuid: String
    ): UserResponse = client.request {
        get(HOST) {
            parameter("uuid", uuid)
        }.body()
    }

    override suspend fun getProfile(): UserResponse = client.request {
        get(HOST).body()
    }

    override suspend fun searchUser(
        query: String,
        page: Int,
        pageSize: Int
    ): UserSearchResponse = client.request {
        get("$HOST/search") {
            parameter("query", query)
            parameter("page", page)
            parameter("page_size", pageSize)
        }.body()
    }

    override suspend fun getFavourites(
        uuid: String,
        page: Int,
        pageSize: Int
    ): UserFavouriteResponse = client.request {
        get("$HOST/favourites") {
            parameter("uuid", uuid)
            parameter("page", page)
            parameter("page_size", pageSize)
        }.body()
    }

    override suspend fun getFollowers(
        uuid: String,
        page: Int,
        pageSize: Int
    ): UserFollowerResponse = client.request {
        get("$HOST/followers") {
            parameter("uuid", uuid)
            parameter("page", page)
            parameter("page_size", pageSize)
        }.body()
    }

    override suspend fun getFollowing(
        uuid: String,
        page: Int,
        pageSize: Int
    ): UserFollowerResponse = client.request {
        get("$HOST/following") {
            parameter("uuid", uuid)
            parameter("page", page)
            parameter("page_size", pageSize)
        }.body()
    }

    companion object {
        private const val HOST = "user"
    }
}