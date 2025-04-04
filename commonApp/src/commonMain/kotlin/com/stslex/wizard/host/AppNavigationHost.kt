package com.stslex.wizard.host

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.stslex.wizard.feature.auth.navigation.AuthInitScreen
import com.stslex.wizard.feature.film.ui.FilmScreen
import com.stslex.wizard.feature.film_feed.ui.FilmFeedScreen

@Composable
internal fun AppNavigationHost(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Children(
            stack = rootComponent.stack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(),
        ) { created ->
            when (val instance = created.instance) {
                is RootComponent.Child.Auth -> AuthInitScreen(instance.component)
                is RootComponent.Child.FeedFilm -> FilmFeedScreen(instance.component)
                is RootComponent.Child.Film -> FilmScreen(instance.filmId, instance.component)
            }
        }
    }
}