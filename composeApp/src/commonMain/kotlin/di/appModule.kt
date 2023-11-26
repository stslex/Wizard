package di

import navigator.AppNavigatorImpl
import com.stslex.core.ui.navigation.AppNavigator
import org.koin.dsl.module

val appModule = module {
    single<AppNavigator> {
        AppNavigatorImpl(
            lazyOf(get())
        )
    }
}