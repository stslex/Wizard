package com.stslex.wizard.feature.profile.data.repository

import com.stslex.wizard.feature.profile.data.model.ProfileDataModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun isCurrentUser(uuid: String): Boolean

    fun getProfile(uuid: String): Flow<ProfileDataModel>
}