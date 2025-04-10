package com.stslex.wizard.host

import com.arkivanov.decompose.Cancellation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.navigation.Config.BottomBar
import com.stslex.wizard.core.navigation.Config.Follower.FollowerType
import com.stslex.wizard.feature.auth.navigation.AuthComponent
import com.stslex.wizard.feature.favourite.navigation.FavouriteComponent
import com.stslex.wizard.feature.film.navigation.FilmComponent
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent
import com.stslex.wizard.feature.follower.navigation.FollowerComponent
import com.stslex.wizard.feature.match.navigation.MatchComponent
import com.stslex.wizard.feature.match_feed.navigation.MatchDetailsComponent
import com.stslex.wizard.feature.profile.navigation.ProfileComponent
import com.stslex.wizard.feature.settings.navigation.SettingsComponent

interface RootComponent {

    val stack: Value<ChildStack<Config, Child>>

    fun onConfigChanged(block: (Config) -> Unit): Cancellation

    fun onBottomAppBarClick(config: BottomBar)

    sealed interface Child {

        data class Auth(
            val component: AuthComponent
        ) : Child

        data class FeedFilm(
            val component: FilmFeedComponent
        ) : Child

        data class Film(
            val component: FilmComponent,
        ) : Child


        data class Favourite(
            val component: FavouriteComponent,
        ) : Child

        data class Follower(
            val component: FollowerComponent,
            val type: FollowerType,
            val uuid: String
        ) : Child

        data class Match(
            val component: MatchComponent,
            val type: BottomBar.Match.Type,
            val uuid: String = ""
        ) : Child

        data class MatchDetails(
            val component: MatchDetailsComponent,
            val uuid: String
        ) : Child

        data class Settings(
            val component: SettingsComponent
        ) : Child

        data class Profile(
            val component: ProfileComponent,
        ) : Child
    }

}