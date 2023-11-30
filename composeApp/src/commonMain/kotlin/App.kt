import androidx.compose.runtime.Composable
import com.stslex.core.core.coreModule
import com.stslex.core.database.di.databaseModule
import com.stslex.core.network.di.networkModule
import com.stslex.core.ui.theme.AppTheme
import com.stslex.feature.film_feed.di.feedModule
import com.stslex.feature.film.di.filmModule
import com.stslex.feature.profile.di.profileModule
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
            networkModule,
            databaseModule,
            feedModule,
            filmModule,
            profileModule,
        )
    )
}