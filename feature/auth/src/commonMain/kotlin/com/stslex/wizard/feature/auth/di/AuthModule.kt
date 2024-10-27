package com.stslex.wizard.feature.auth.di

import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.auth.data.AuthRepository
import com.stslex.wizard.feature.auth.data.AuthRepositoryImpl
import com.stslex.wizard.feature.auth.domain.AuthInteractor
import com.stslex.wizard.feature.auth.domain.AuthInteractorImpl
import com.stslex.wizard.feature.auth.navigation.AuthRouter
import com.stslex.wizard.feature.auth.navigation.AuthRouterImpl
import com.stslex.wizard.feature.auth.ui.store.AuthStore
import org.koin.dsl.module

val featureAuthModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(client = get()) }
    factory<AuthInteractor> { AuthInteractorImpl(authRepository = get()) }
    factory<AuthRouter> { AuthRouterImpl(navigator = get()) }
    storeDefinition {
        AuthStore(
            interactor = get(),
            router = get(),
            dispatcher = get()
        )
    }
}