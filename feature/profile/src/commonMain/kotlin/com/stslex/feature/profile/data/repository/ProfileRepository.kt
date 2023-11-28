package com.stslex.feature.profile.data.repository

import com.stslex.feature.profile.data.model.ProfileDataModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(uuid: String): Flow<ProfileDataModel>
}