package com.stslex.wizard.di

import androidx.navigation.NavHostController
import com.stslex.wizard.core.core.coreModule
import com.stslex.wizard.core.database.di.coreDatabaseModule
import com.stslex.wizard.core.database.di.userSettingsModule
import com.stslex.wizard.core.navigation.di.ModuleCoreNavigation
import com.stslex.wizard.core.network.di.coreNetworkModule
import com.stslex.wizard.core.ui.di.coreUiModule
import com.stslex.wizard.feature.auth.di.featureAuthModule
import com.stslex.wizard.feature.favourite.di.featureFavouriteModule
import com.stslex.wizard.feature.film.di.featureFilmModule
import com.stslex.wizard.feature.film_feed.di.featureFeedModule
import com.stslex.wizard.feature.follower.di.featureFollowerModule
import com.stslex.wizard.feature.match.di.featureMatchModule
import com.stslex.wizard.feature.match_feed.di.featureMatchFeedModule
import com.stslex.wizard.feature.profile.di.featureProfileModule
import com.stslex.wizard.feature.settings.di.featureSettingsModule

fun appModules(navHostController: NavHostController) = listOf(
    coreModule,
    coreUiModule,
    coreNetworkModule,
    ModuleCoreNavigation(navHostController).module,
    userSettingsModule,
    coreDatabaseModule,
    featureFeedModule,
    featureFilmModule,
    featureProfileModule,
    featureMatchFeedModule,
    featureAuthModule,
    featureFollowerModule,
    featureFavouriteModule,
    featureSettingsModule,
    featureMatchModule,
)