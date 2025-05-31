package com.stslex.wizard.host

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.stslex.wizard.feature.auth.ui.AuthInitScreen
import com.stslex.wizard.feature.favourite.ui.FavouriteScreen
import com.stslex.wizard.feature.film.ui.FilmScreen
import com.stslex.wizard.feature.film_feed.ui.FilmFeedScreen
import com.stslex.wizard.feature.follower.ui.FollowerScreen
import com.stslex.wizard.feature.match.ui.MatchScreen
import com.stslex.wizard.feature.match_feed.ui.MatchDetailsScreen
import com.stslex.wizard.feature.profile.ui.ProfileScreen
import com.stslex.wizard.feature.settings.ui.SettingsScreen

@Composable
internal fun AppNavigationHost(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = rootComponent.stack,
        modifier = modifier.fillMaxSize(),
        animation = stackAnimation(),
    ) { created ->
        when (val instance = created.instance) {
            is RootComponent.Child.Auth -> AuthInitScreen(instance.component)
            is RootComponent.Child.FeedFilm -> FilmFeedScreen(instance.component)
            is RootComponent.Child.Film -> FilmScreen(instance.component)
            is RootComponent.Child.Favourite -> FavouriteScreen(instance.component)
            is RootComponent.Child.Follower -> FollowerScreen(instance.component)
            is RootComponent.Child.Match -> MatchScreen(instance.component)
            is RootComponent.Child.MatchDetails -> MatchDetailsScreen(instance.component)
            is RootComponent.Child.Profile -> ProfileScreen(instance.component)
            is RootComponent.Child.Settings -> SettingsScreen(instance.component)
        }
    }
}