package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.api.server.client.ServerApiClient
import com.stslex.core.network.clients.profile.model.request.AddLikeRequest
import com.stslex.core.network.clients.profile.model.response.BooleanResponse
import com.stslex.core.network.clients.profile.model.response.UserFavouriteResponse
import com.stslex.core.network.clients.profile.model.response.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.response.UserResponse
import com.stslex.core.network.clients.profile.model.response.UserSearchResponse
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

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
        query: String,
        page: Int,
        pageSize: Int
    ): UserFavouriteResponse = client.request {
        get("$HOST/favourites") {
            parameter("uuid", uuid)
            parameter("query", query)
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

    override suspend fun addFavourite(uuid: String, title: String) {
        client.request {
            post("$HOST/favourite") {
                setBody(
                    AddLikeRequest(
                        filmUuid = uuid,
                        title = title
                    )
                )
            }
        }
    }

    override suspend fun removeFavourite(uuid: String) {
        client.request {
            delete("$HOST/favourite") {
                parameter("favourite_uuid", uuid)
            }
        }
    }

    override suspend fun isFavourite(
        favouriteUuid: String
    ): BooleanResponse = client.request {
        get("$HOST/is_favourite") {
            parameter("uuid", favouriteUuid)
        }.body()
    }

    companion object {
        private const val HOST = "user"
    }
}

