package com.stslex.core.ui.navigation

sealed interface AppScreen {

    data object Main : AppScreen

    data class Film(val id: String) : AppScreen

    data object Back : AppScreen

    data object MatchFeed : AppScreen

    data object Auth : AppScreen

    data class Favourite(
        val uuid: String
    ) : AppScreen

    data class Following(
        val uuid: String
    ) : AppScreen

    data class Followers(
        val uuid: String
    ) : AppScreen
}