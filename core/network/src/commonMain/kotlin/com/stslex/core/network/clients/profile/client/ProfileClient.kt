package com.stslex.core.network.clients.profile.client

import com.stslex.core.network.clients.profile.model.ProfileResponse

interface ProfileClient {

    suspend fun getProfile(uuid: String): ProfileResponse

    suspend fun getProfile(): ProfileResponse
}