import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.stslex.core.database.store.UserStore
import com.stslex.feature.auth.ui.AuthScreen
import main_screen.MainScreen
import org.koin.compose.getKoin

@Composable
fun InitialApp(
    modifier: Modifier = Modifier
) {
    val userStore = getKoin().get<UserStore>()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
        ) {
            Navigator(
                screen = if (userStore.isAuth) MainScreen else AuthScreen
            ) {
                SlideTransition(it)
            }
        }
    }
}
