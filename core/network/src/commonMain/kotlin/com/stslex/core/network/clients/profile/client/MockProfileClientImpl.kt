package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.clients.profile.model.ProfileResponse
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
}