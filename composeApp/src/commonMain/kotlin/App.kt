import androidx.compose.runtime.Composable
import com.stslex.core.core.coreModule
import com.stslex.core.database.di.coreDatabaseModule
import com.stslex.core.database.di.userSettingsModule
import com.stslex.core.network.di.coreNetworkModule
import com.stslex.core.ui.theme.AppTheme
import com.stslex.feature.film.di.featureFilmModule
import com.stslex.feature.film_feed.di.featureFeedModule
import com.stslex.feature.match_feed.di.featureMatchFeedModule
import com.stslex.feature.profile.di.featureProfileModule
import di.appModule
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
            coreNetworkModule,
            userSettingsModule,
            coreDatabaseModule,
            featureFeedModule,
            featureFilmModule,
            featureProfileModule,
            featureMatchFeedModule
        )
    )
}
