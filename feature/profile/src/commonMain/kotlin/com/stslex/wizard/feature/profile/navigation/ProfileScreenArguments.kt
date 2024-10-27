package com.stslex.wizard.feature.profile.navigation

sealed interface ProfileScreenArguments {

    data object Self : ProfileScreenArguments

    data class Other(
        private val id: String
    ) : ProfileScreenArguments

    val isSelf: Boolean
        get() = this is Self

    val uuid: String?
        get() = (this as? Other)?.uuid
}