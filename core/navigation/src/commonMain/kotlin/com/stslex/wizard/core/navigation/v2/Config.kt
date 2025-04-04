package com.stslex.wizard.core.navigation.v2

import kotlinx.serialization.Serializable

@Serializable
sealed interface Config {

    data object Auth : Config

    data class Film(val id: String) : Config

    data object FeedFilm : Config

    data object Favourite : Config

    data object Profile : Config

    data object Match : Config

    data object MatchFeed : Config

    data object Settings : Config

    data object Follower : Config
}