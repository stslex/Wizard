package com.stslex.wizard.feature.profile.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.feature.profile.ui.model.ProfileModel

@Stable
sealed interface ProfileScreenState {

    @Stable
    sealed class Content(
        val data: ProfileModel
    ) : ProfileScreenState {

        data class NotLoading(
            val profile: ProfileModel
        ) : Content(profile)

        data class Loading(
            val profile: ProfileModel
        ) : Content(profile)
    }

    @Stable
    data object Shimmer : ProfileScreenState

    @Stable
    data class Error(val error: Throwable) : ProfileScreenState
}