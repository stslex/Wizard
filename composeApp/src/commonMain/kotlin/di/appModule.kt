package di

import com.stslex.core.ui.navigation.AppNavigator
import navigator.AppNavigatorImpl
import org.koin.dsl.module

val appModule = module {
    single<AppNavigator> {
        AppNavigatorImpl()
    }
}