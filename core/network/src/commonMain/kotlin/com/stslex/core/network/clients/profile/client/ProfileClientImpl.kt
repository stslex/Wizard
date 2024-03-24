package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.api.server.client.ServerApiClient
import com.stslex.core.network.clients.profile.model.request.AddLikeRequest
import com.stslex.core.network.clients.profile.model.request.PagingRequest
import com.stslex.core.network.clients.profile.model.response.BooleanResponse
import com.stslex.core.network.clients.profile.model.response.PagingResponse
import com.stslex.core.network.clients.profile.model.response.UserFavouriteResultResponse
import com.stslex.core.network.clients.profile.model.response.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.response.UserResponse
import com.stslex.core.network.clients.profile.model.response.UserSearchResponse
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
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
        request: PagingRequest
    ): UserSearchResponse = client.request {
        get("$HOST/search") {
            requestPaging(request)
        }.body()
    }

    override suspend fun getFavourites(
        request: PagingRequest
    ): PagingResponse<UserFavouriteResultResponse> = client.request {
        get("$HOST/$HOST_FAVOURITE") {
            requestPaging(request)
        }.body()
    }

    override suspend fun getFollowers(
        request: PagingRequest
    ): UserFollowerResponse = client.request {
        get("$HOST/$HOST_FOLLOW") {
            requestPaging(request)
        }.body()
    }

    override suspend fun getFollowing(
        request: PagingRequest
    ): UserFollowerResponse = client.request {
        get("$HOST/$HOST_FOLLOW") {
            requestPaging(request)
        }.body()
    }

    override suspend fun addFavourite(uuid: String, title: String) {
        client.request {
            post("$HOST/$HOST_FAVOURITE") {
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
            delete("$HOST/$HOST_FAVOURITE") {
                parameter("favourite_uuid", uuid)
            }
        }
    }

    override suspend fun isFavourite(
        favouriteUuid: String
    ): BooleanResponse = client.request {
        get("$HOST/is_favourite") {
            parameter(PARAMETER_UUID, favouriteUuid)
        }.body()
    }

    private fun HttpRequestBuilder.requestPaging(request: PagingRequest) {
        if (request.uuid != null) {
            parameter(PARAMETER_UUID, request.uuid)
        }
        parameter(PARAMETER_QUERY, request.query)
        parameter(PARAMETER_PAGE, request.page)
        parameter(PARAMETER_PAGE_SIZE, request.pageSize)
    }

    companion object {
        private const val HOST = "user"

        private const val HOST_FAVOURITE = "favourite"
        private const val HOST_FOLLOW = "follow"

        private const val PARAMETER_QUERY = "query"
        private const val PARAMETER_PAGE = "page"
        private const val PARAMETER_PAGE_SIZE = "page_size"
        private const val PARAMETER_UUID = "uuid"
    }
}

