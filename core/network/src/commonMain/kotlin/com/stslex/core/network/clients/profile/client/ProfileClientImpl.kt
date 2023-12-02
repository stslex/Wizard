package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.api.base.NetworkClient
import com.stslex.core.network.clients.profile.model.ProfileResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ProfileClientImpl(
    private val client: NetworkClient
) : ProfileClient {

    override suspend fun getProfile(
        uuid: String
    ): ProfileResponse = client.request {
        get("profile") {
            parameter("uuid", uuid)
        }.body()
    }
}