package com.stslex.wizard.di

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.wizard.navigator.AppNavigatorImpl
import org.koin.dsl.module

val appModule = module {
    single<AppNavigator> {
        AppNavigatorImpl()
    }
}