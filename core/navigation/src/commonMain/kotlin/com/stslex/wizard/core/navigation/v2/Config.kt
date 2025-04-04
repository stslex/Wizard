package com.stslex.wizard.core.navigation.v2

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Serializable
@Stable
sealed interface Config {

    sealed interface BottomBar : Config {

        data object FilmFeed : BottomBar

        data class Match(
            val type: Type,
            val uuid: String = ""
        ) : BottomBar {

            enum class Type { SELF, OTHER }
        }

        @Serializable
        data class Profile(
            val type: Type,
            val uuid: String = ""
        ) : BottomBar {

            enum class Type { SELF, OTHER }
        }
    }

    data object Auth : Config

    data class Film(val id: String) : Config

    data object Favourite : Config

    data object MatchFeed : Config

    data object Settings : Config

    data object Follower : Config
}