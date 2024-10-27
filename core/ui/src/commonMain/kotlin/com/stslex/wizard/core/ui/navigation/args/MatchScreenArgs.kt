package com.stslex.wizard.core.ui.navigation.args

import androidx.compose.runtime.Stable

@Stable
interface MatchScreenArgs {

    @Stable
    data object Self : MatchScreenArgs

    @Stable
    data class Other(
        private val id: String
    ) : MatchScreenArgs

    val isSelf: Boolean
        get() = this is Self

    val uuid: String?
        get() = (this as? Other)?.uuid
}