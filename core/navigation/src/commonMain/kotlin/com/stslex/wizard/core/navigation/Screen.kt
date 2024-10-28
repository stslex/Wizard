package com.stslex.wizard.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

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
    data class Follower(
        val type: FollowerType,
        val uuid: String
    ) : Screen {

        enum class FollowerType { FOLLOWER, FOLLOWING }
    }

    @Serializable
    data class MatchDetails(val matchUuid: String) : Screen

    @Serializable
    data object FilmFeed : Screen

    @Serializable
    data class Match(
        val type: Type,
        val uuid: String = ""
    ) : Screen {

        enum class Type { SELF, OTHER }
    }

    @Serializable
    data class Profile(
        val type: Type,
        val uuid: String = ""
    ) : Screen {

        enum class Type { SELF, OTHER }
    }
}

