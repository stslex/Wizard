package com.stslex.wizard.core.navigation

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Serializable
@Stable
sealed interface Config {

    val isBackAllow: Boolean
        get() = true

    sealed interface BottomBar : Config {

        override val isBackAllow: Boolean
            get() = false

        data object FilmFeed : BottomBar

        data class Match(
            val type: Type,
            val uuid: String = ""
        ) : BottomBar {

            enum class Type { SELF, OTHER }
        }

        data class Profile(
            val type: Type,
            val uuid: String = ""
        ) : BottomBar {

            enum class Type { SELF, OTHER }
        }
    }

    data object Auth : Config

    data class Film(
        val uuid: String
    ) : Config

    data class Favourite(
        val uuid: String
    ) : Config

    data class MatchDetails(
        val uuid: String
    ) : Config

    data object Settings : Config

    data class Follower(
        val type: FollowerType,
        val uuid: String
    ) : Config {

        enum class FollowerType { FOLLOWER, FOLLOWING }
    }
}