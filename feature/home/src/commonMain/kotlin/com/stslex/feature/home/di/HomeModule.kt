package com.stslex.feature.home.di

import com.stslex.core.ui.base.viewModelDefinition
import com.stslex.feature.home.navigation.HomeScreenRouter
import com.stslex.feature.home.navigation.HomeScreenRouterImpl
import com.stslex.feature.home.ui.store.HomeScreenStore
import org.koin.dsl.module

val homeModule = module {
    viewModelDefinition { HomeScreenStore(get(), get()) }
    factory<HomeScreenRouter> {
        HomeScreenRouterImpl(get())
    }
}
