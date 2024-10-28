package com.stslex.wizard.core.navigation

import com.stslex.wizard.core.navigation.args.MatchScreenArgs
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Main : Screen

    @Serializable
    data class Film(val id: String) : Screen

    @Serializable
    data object MatchFeed : Screen

    @Serializable
    data object Auth : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data class Favourite(val uuid: String) : Screen

    @Serializable
    data class Following(val uuid: String) : Screen

    @Serializable
    data class Followers(val uuid: String) : Screen

    @Serializable
    data class MatchDetails(val matchUuid: String) : Screen

    @Serializable
    data class Match(val args: MatchScreenArgs) : Screen
}

