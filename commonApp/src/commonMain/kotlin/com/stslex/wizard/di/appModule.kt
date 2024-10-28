package com.stslex.wizard.di

import androidx.navigation.NavHostController
import com.stslex.wizard.core.core.ModuleCore
import com.stslex.wizard.core.core.modules
import com.stslex.wizard.core.database.di.ModuleCoreDatabase
import com.stslex.wizard.core.navigation.di.ModuleCoreNavigation
import com.stslex.wizard.core.network.di.ModuleCoreNetwork
import com.stslex.wizard.core.ui.di.ModuleCoreUi
import com.stslex.wizard.feature.auth.di.ModuleFeatureAuth
import com.stslex.wizard.feature.favourite.di.ModuleFeatureFavourite
import com.stslex.wizard.feature.film.di.ModuleFeatureFilm
import com.stslex.wizard.feature.film_feed.di.ModuleFeatureFeed
import com.stslex.wizard.feature.follower.di.ModuleFeatureFollower
import com.stslex.wizard.feature.match.di.ModuleFeatureMatch
import com.stslex.wizard.feature.match_feed.di.ModuleFeatureMatchFeed
import com.stslex.wizard.feature.profile.di.ModuleFeatureProfile
import com.stslex.wizard.feature.settings.di.ModuleFeatureSettings

fun appModules(navHostController: NavHostController) = listOf(
    ModuleCore(),
    ModuleCoreUi(),
    ModuleCoreNetwork(),
    ModuleCoreNavigation(navHostController),
    ModuleCoreDatabase(),
    ModuleFeatureFeed(),
    ModuleFeatureFilm(),
    ModuleFeatureProfile(),
    ModuleFeatureMatchFeed(),
    ModuleFeatureAuth(),
    ModuleFeatureFollower(),
    ModuleFeatureFavourite(),
    ModuleFeatureSettings(),
    ModuleFeatureMatch(),
).modules