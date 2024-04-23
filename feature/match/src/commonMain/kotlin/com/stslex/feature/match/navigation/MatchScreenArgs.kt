package com.stslex.feature.match.navigation

interface MatchScreenArgs {

    data object Self : MatchScreenArgs

    data class Other(
        private val id: String
    ) : MatchScreenArgs

    val isSelf: Boolean
        get() = this is Self

    val uuid: String?
        get() = (this as? Other)?.uuid
}