package com.stslex.wizard.feature.profile.di

import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.profile.data.repository.ProfileRepository
import com.stslex.wizard.feature.profile.data.repository.ProfileRepositoryImpl
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractorImpl
import com.stslex.wizard.feature.profile.navigation.ProfileRouter
import com.stslex.wizard.feature.profile.navigation.ProfileRouterImpl
import com.stslex.wizard.feature.profile.ui.store.ProfileStore
import org.koin.dsl.module

val featureProfileModule = module {
    storeDefinition {
        ProfileStore(
            interactor = get(),
            userStore = get(),
            appDispatcher = get(),
            router = get(),
        )
    }
    factory<ProfileRouter> { ProfileRouterImpl(navigator = get()) }
    factory<ProfileRepository> {
        ProfileRepositoryImpl(
            client = get(),
            dataStore = get(),
        )
    }
    factory<ProfileInteractor> {
        ProfileInteractorImpl(
            repository = get(),
            authController = get(),
        )
    }
}