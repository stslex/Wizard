import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.feature.home.HomeScreen
import com.stslex.feature.home.di.homeModule
import org.koin.core.context.startKoin

@Composable
fun InitialApp() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        HomeScreen()
    }
}
