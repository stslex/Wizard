package com.stslex.wizard.feature.profile.domain.interactor

import com.stslex.wizard.core.network.api.utils.token.AuthController
import com.stslex.wizard.feature.profile.data.repository.ProfileRepository
import com.stslex.wizard.feature.profile.domain.model.ProfileDomainModel
import com.stslex.wizard.feature.profile.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class ProfileInteractorImpl(
    private val repository: ProfileRepository,
    private val authController: AuthController
) : ProfileInteractor {

    override fun getProfile(
        uuid: String
    ): Flow<ProfileDomainModel> = repository.getProfile(uuid).map { it.toDomain() }

    override suspend fun logOut() {
        authController.logout()
    }
}