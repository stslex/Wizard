package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.api.server.ServerApiClient
import com.stslex.core.network.clients.profile.model.ProfileResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ProfileClientImpl(
    private val client: ServerApiClient
) : ProfileClient {

    override suspend fun getProfile(
        uuid: String
    ): ProfileResponse = client.request {
        get(HOST){
            parameter("uuid", uuid)
        }.body()
    }

    override suspend fun getProfile(): ProfileResponse = client.request {
        get(HOST).body()
    }

    companion object {
        private const val HOST = "user"
    }
}