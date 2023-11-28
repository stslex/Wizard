package com.stslex.feature.profile.ui.store

import androidx.compose.runtime.Stable
import com.stslex.feature.profile.ui.model.ProfileModel

@Stable
sealed interface ProfileScreenState {

    @Stable
    data class Content(val model: ProfileModel) : ProfileScreenState

    @Stable
    data object Loading : ProfileScreenState

    @Stable
    data class Error(val error: Throwable) : ProfileScreenState
}