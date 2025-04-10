package com.stslex.wizard.feature.profile.domain.interactor

import com.stslex.wizard.feature.profile.domain.model.ProfileDomainModel
import kotlinx.coroutines.flow.Flow

interface ProfileInteractor {

    fun getProfile(uuid: String): Flow<ProfileDomainModel>

    suspend fun logOut()
}