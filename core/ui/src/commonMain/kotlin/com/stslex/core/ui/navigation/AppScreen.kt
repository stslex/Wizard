package com.stslex.core.ui.navigation

sealed interface AppScreen {

    data object Home : AppScreen

    data class SecondScreen(val text: String) : AppScreen
}