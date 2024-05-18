package com.stslex.feature.settings.di

import com.stslex.core.ui.mvi.viewModelDefinition
import com.stslex.feature.settings.domain.SettingsInteractor
import com.stslex.feature.settings.domain.SettingsInteractorImpl
import com.stslex.feature.settings.navigation.SettingsRouter
import com.stslex.feature.settings.navigation.SettingsRouterImpl
import com.stslex.feature.settings.ui.store.SettingsStore
import com.stslex.feature.settings.ui.store.SettingsStoreImpl
import org.koin.dsl.module

val featureSettingsModule = module {
    viewModelDefinition<SettingsStore, SettingsStoreImpl> {
        SettingsStoreImpl(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
        )
    }
    factory<SettingsRouter> { SettingsRouterImpl(navigator = get()) }
    factory<SettingsInteractor> { SettingsInteractorImpl(authController = get()) }
}