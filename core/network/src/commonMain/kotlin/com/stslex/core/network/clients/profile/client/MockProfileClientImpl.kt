package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.clients.profile.model.ProfileResponse
import com.stslex.core.network.clients.profile.model.UserFavouriteResponse
import com.stslex.core.network.clients.profile.model.UserFavouriteResultResponse
import com.stslex.core.network.clients.profile.model.UserFollowerResponse
import com.stslex.core.network.clients.profile.model.UserFollowerResultResponse
import com.stslex.core.network.clients.profile.model.UserSearchResponse
import com.stslex.core.network.clients.profile.model.UserSearchResultResponse
import kotlinx.coroutines.delay

class MockProfileClientImpl : ProfileClient {

    override suspend fun getProfile(uuid: String): ProfileResponse {
        delay(2000)
        return ProfileResponse(
            uuid = "uuid",
            username = "John Doe",
            avatarUrl = "https://avatars.githubusercontent.com/u/139426?s=460&u=8f6b6e2e4e9e4b0e9b5b5e4e9b5b5e4e9b5b5e4e&v=4",
            bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eu ultricies tincidunt, nisl nisl aliquam nisl,",
            followers = 100,
            following = 93,
            favouriteCount = 873
        )
    }

    override suspend fun getProfile(): ProfileResponse = getProfile("uuid")

    override suspend fun searchUser(
        query: String,
        page: Int,
        pageSize: Int
    ): UserSearchResponse {
        delay(2000)
        return UserSearchResponse(
            result = listOf(
                UserSearchResultResponse(
                    uuid = "uuid",
                    username = "John Doe",
                    avatarUrl = "https://avatars.githubusercontent.com/u/139426?s=460&u=8f6b6e2e4e9e4b0e9b5b5e4e9b5b5e4e9b5b5e4e&v=4",
                    bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eu ultricies tincidunt, nisl nisl aliquam nisl,",
                    followersCount = 100,
                    followingCount = 93,
                    favouritesCount = 873
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
                ),
            )
        )
    }
}