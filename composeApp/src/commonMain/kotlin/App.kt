import androidx.compose.runtime.Composable
import com.stslex.core.core.coreModule
import com.stslex.core.database.di.coreDatabaseModule
import com.stslex.core.network.di.coreNetworkModule
import com.stslex.core.ui.theme.AppTheme
import com.stslex.feature.film.di.featureFilmModule
import com.stslex.feature.film_feed.di.featureFeedModule
import com.stslex.feature.match_feed.di.featureMatchFeedModule
import com.stslex.feature.profile.di.featureProfileModule
import di.appModule
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinAppDeclaration

@Composable
fun App() {
    SetupKoin {
        AppTheme {
            InitialApp()
        }
    }
}

@Composable
fun SetupKoin(
    content: @Composable () -> Unit
) {
    KoinApplication(
        application = setupModules(),
        content = content
    )
}

private fun setupModules(): KoinAppDeclaration = {
    modules(
        listOf(
            appModule,
            coreModule,
            coreNetworkModule,
            coreDatabaseModule,
            featureFeedModule,
            featureFilmModule,
            featureProfileModule,
            featureMatchFeedModule
        )
    )
}