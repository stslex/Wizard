package com.stslex.wizard.feature.profile.data.repository

import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.network.api.clients.profile.client.ProfileClient
import com.stslex.wizard.feature.profile.data.model.ProfileDataModel
import com.stslex.wizard.feature.profile.data.model.toData
import com.stslex.wizard.feature.profile.di.ProfileScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(ProfileScope::class)
@Scoped
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