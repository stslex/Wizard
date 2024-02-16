package com.stslex.feature.profile.ui.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
sealed class ProfileAvatarModel {

    @Stable
    data class Empty(
        val symbol: String,
        val color: Color,
    ) : ProfileAvatarModel()

    @Stable
    data class Content(
        val url: String
    ) : ProfileAvatarModel()
}