package com.stslex.core.network.clients.profile.client

import com.stslex.core.core.Logger
import com.stslex.core.network.clients.profile.model.response.BooleanResponse
import com.stslex.core.network.clients.profile.model.response.UserFavouriteResponse
import com.stslex.core.network.clients.profile.model.response.UserFavouriteResultResponse
import com.stslex.core.network.clients.profile.model.response.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.response.UserFollowerResultResponse
import com.stslex.core.network.clients.profile.model.response.UserResponse
import com.stslex.core.network.clients.profile.model.response.UserSearchResponse
import kotlinx.coroutines.delay

class MockProfileClientImpl : ProfileClient {

    override suspend fun getProfile(uuid: String): UserResponse {
        delay(2000)
        return UserResponse(
            uuid = "uuid",
            username = "John Doe",
            avatarUrl = "https://avatars.githubusercontent.com/u/139426?s=460&u=8f6b6e2e4e9e4b0e9b5b5e4e9b5b5e4e9b5b5e4e&v=4",
            bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eu ultricies tincidunt, nisl nisl aliquam nisl,",
            followersCount = 100,
            followingCount = 93,
            favouritesCount = 873,
            isFollowing = false,
            isFollowed = false,
            isCurrentUser = true,
        )
    }

    override suspend fun getProfile(): UserResponse = getProfile("uuid")

    override suspend fun searchUser(
        query: String,
        page: Int,
        pageSize: Int
    ): UserSearchResponse {
        delay(2000)
        return UserSearchResponse(
            result = listOf(
                UserResponse(
                    uuid = "uuid",
                    username = "John Doe",
                    avatarUrl = "https://avatars.githubusercontent.com/u/139426?s=460&u=8f6b6e2e4e9e4b0e9b5b5e4e9b5b5e4e9b5b5e4e&v=4",
                    bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eu ultricies tincidunt, nisl nisl aliquam nisl,",
                    followersCount = 100,
                    followingCount = 93,
                    favouritesCount = 873,
                    isFollowing = true,
                    isFollowed = true,
                    isCurrentUser = true,
                ),
            )
        )
    }

    override suspend fun getFavourites(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): UserFavouriteResponse {
        delay(2000)
        return UserFavouriteResponse(
            result = listOf(
                UserFavouriteResultResponse(
                    uuid = "uuid",
                    title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eu ultricies tincidunt, nisl nisl aliquam nisl,",
                    isFavourite = false,
                ),
            )
        )
    }

    override suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): UserFollowerResponse {
        delay(2000)
        return UserFollowerResponse(
            result = listOf(
                UserFollowerResultResponse(
                    uuid = "uuid",
                    username = "John Doe",
                    avatarUrl = "https://avatars.githubusercontent.com/u/139426?s=460&u=8f6b6e2e4e9e4b0e9b5b5e4e9b5b5e4e9b5b5e4e&v=4",
                    isFollowing = false,
                ),
            )
        )
    }

    override suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): UserFollowerResponse {
        delay(2000)
        return UserFollowerResponse(
            result = listOf(
                UserFollowerResultResponse(
                    uuid = "uuid",
                    username = "John Doe",
                    avatarUrl = "https://avatars.githubusercontent.com/u/139426?s=460&u=8f6b6e2e4e9e4b0e9b5b5e4e9b5b5e4e9b5b5e4e&v=4",
                    isFollowing = false
                ),
            )
        )
    }

    override suspend fun addFavourite(uuid: String, title: String) {
        Logger.debug("Favourite added")
    }

    override suspend fun isFavourite(favouriteUuid: String): BooleanResponse {
        return BooleanResponse(true)
    }

    override suspend fun removeFavourite(uuid: String) {
        Logger.debug("Favourite removed")
    }
}