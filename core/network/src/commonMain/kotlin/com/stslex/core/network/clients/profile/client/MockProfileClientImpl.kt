package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.clients.profile.model.UserFavouriteResponse
import com.stslex.core.network.clients.profile.model.UserFavouriteResultResponse
import com.stslex.core.network.clients.profile.model.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.UserFollowerResultResponse
import com.stslex.core.network.clients.profile.model.UserResponse
import com.stslex.core.network.clients.profile.model.UserSearchResponse
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
        page: Int,
        pageSize: Int
    ): UserFavouriteResponse {
        delay(2000)
        return UserFavouriteResponse(
            result = listOf(
                UserFavouriteResultResponse(
                    uuid = "uuid",
                    title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eu ultricies tincidunt, nisl nisl aliquam nisl,",
                ),
            )
        )
    }

    override suspend fun getFollowers(
        uuid: String,
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
}