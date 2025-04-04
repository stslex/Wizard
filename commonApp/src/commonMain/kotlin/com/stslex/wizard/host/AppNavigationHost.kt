package com.stslex.wizard.host

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.feature.auth.navigation.graphAuth
import com.stslex.wizard.feature.favourite.navigation.graphFavourite
import com.stslex.wizard.feature.follower.navigation.graphFollower
import com.stslex.wizard.feature.match.navigation.graphMatch
import com.stslex.wizard.feature.match_feed.navigation.graphMatchFeed
import com.stslex.wizard.feature.profile.navigation.graphProfile
import com.stslex.wizard.feature.settings.navigation.graphSettings

@Composable
fun AppNavigationHost(
    navHostController: NavHostController,
    startScreen: Screen,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        NavHost(
            navController = navHostController,
            startDestination = startScreen
        ) {
            graphAuth()
            graphFavourite()
//            graphFilm()
//            graphFilmFeed() refactor
            graphFollower()
            graphMatch()
            graphMatchFeed()
            graphProfile()
            graphSettings()
        }
    }
}