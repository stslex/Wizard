package com.stslex.wizard.feature.profile.data.repository

import com.stslex.wizard.core.database.store.UserStore
import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.feature.profile.data.model.ProfileDataModel
import com.stslex.feature.profile.data.model.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImpl(
    private val client: ProfileClient,
    private val dataStore: UserStore
) : ProfileRepository {

    override fun isCurrentUser(uuid: String): Boolean = dataStore.uuid == uuid

    override fun getProfile(
        uuid: String
    ): Flow<ProfileDataModel> = flow {
        val profile = if (isCurrentUser(uuid)) {
            client.getProfile()
        } else {
            client.getProfile(uuid)
        }.toData()
        emit(profile)
    }
}