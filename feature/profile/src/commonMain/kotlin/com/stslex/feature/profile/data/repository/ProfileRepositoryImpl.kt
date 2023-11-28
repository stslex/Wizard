package com.stslex.feature.profile.data.repository

import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.feature.profile.data.model.ProfileDataModel
import com.stslex.feature.profile.data.model.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImpl(
    private val client: ProfileClient
) : ProfileRepository {

    override fun getProfile(uuid: String): Flow<ProfileDataModel> = flow {
        val profile = client.getProfile(uuid).toData()
        emit(profile)
    }
}