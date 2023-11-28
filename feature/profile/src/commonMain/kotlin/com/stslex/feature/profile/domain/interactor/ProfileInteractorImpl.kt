package com.stslex.feature.profile.domain.interactor

import com.stslex.feature.profile.data.repository.ProfileRepository
import com.stslex.feature.profile.domain.model.ProfileDomainModel
import com.stslex.feature.profile.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileInteractorImpl(
    private val repository: ProfileRepository
) : ProfileInteractor {

    override fun getProfile(
        uuid: String
    ): Flow<ProfileDomainModel> = repository.getProfile(uuid).map { it.toDomain() }
}