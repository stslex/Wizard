package com.stslex.wizard.di

import com.stslex.wizard.core.core.ModuleCore
import com.stslex.wizard.core.core.modules
import com.stslex.wizard.core.database.di.ModuleCoreDatabase
import com.stslex.wizard.core.network.api.di.ModuleCoreNetworkApi
import com.stslex.wizard.core.network.client.di.ModuleCoreNetworkClient
import com.stslex.wizard.core.ui.kit.di.ModuleCoreUi
import com.stslex.wizard.feature.film.di.ModuleFeatureFilm
import com.stslex.wizard.feature.follower.di.ModuleFeatureFollower
import com.stslex.wizard.feature.match.di.ModuleFeatureMatch
import com.stslex.wizard.feature.match_feed.di.ModuleFeatureMatchFeed
import com.stslex.wizard.feature.settings.di.ModuleFeatureSettings

val appModules = listOf(
    ModuleCore(),
    ModuleCoreUi(),
    ModuleCoreNetworkClient(),
    ModuleCoreNetworkApi(),
    ModuleCoreDatabase(),
    ModuleFeatureMatchFeed(),
    ModuleFeatureFilm(),
    ModuleFeatureFollower(),
    ModuleFeatureSettings(),
    ModuleFeatureMatch()
).modules


