package com.stslex.wizard

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.core.coreModule
import com.stslex.wizard.core.database.di.coreDatabaseModule
import com.stslex.wizard.core.database.di.userSettingsModule
import com.stslex.core.ui.di.coreUiModule
import com.stslex.core.ui.theme.AppTheme
import com.stslex.feature.auth.di.featureAuthModule
import com.stslex.wizard.feature.favourite.di.featureFavouriteModule
import com.stslex.wizard.feature.film.di.featureFilmModule
import com.stslex.feature.film_feed.di.featureFeedModule
import com.stslex.wizard.feature.follower.di.featureFollowerModule
import com.stslex.wizard.feature.match.di.featureMatchModule
import com.stslex.feature.match_feed.di.featureMatchFeedModule
import com.stslex.feature.profile.di.featureProfileModule
import com.stslex.wizard.feature.settings.di.featureSettingsModule
import com.stslex.wizard.di.appModule
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

@Composable
fun App(
    additionalSetup: KoinApplication.() -> Unit = {},
) {
    KoinApplication(
        application = {
            setupCommonModules()
            additionalSetup()
        }
    ) {
        AppTheme {
            InitialApp()
        }
    }
}

private fun KoinApplication.setupCommonModules() {
    modules(
        listOf(
            appModule,
            coreModule,
            coreUiModule,
            com.stslex.wizard.core.network.di.coreNetworkModule,
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
            featureMatchModule
        )
    )
}
