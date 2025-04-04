package com.stslex.wizard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.stslex.wizard.core.ui.kit.theme.AppTheme
import com.stslex.wizard.feature.auth.navigation.AuthInitScreen
import com.stslex.wizard.feature.film.ui.FilmScreen
import com.stslex.wizard.feature.film_feed.ui.FilmFeedScreen
import com.stslex.wizard.host.RootComponent

@Composable
fun App(rootComponent: RootComponent) {
    AppTheme {
//        InitialApp(navHostController)

        Children(
            stack = rootComponent.stack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(fade().plus(scale())),
        ) { created ->
            when (val instance = created.instance) {
                is RootComponent.Child.Auth -> AuthInitScreen(instance.component)
                is RootComponent.Child.FeedFilm -> FilmFeedScreen(instance.component)
                is RootComponent.Child.Film -> FilmScreen(instance.filmId, instance.component)
            }
        }
    }
}