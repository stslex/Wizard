package com.stslex.wizard.feature.auth.di

import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.auth.data.AuthRepository
import com.stslex.wizard.feature.auth.data.AuthRepositoryImpl
import com.stslex.wizard.feature.auth.domain.AuthInteractor
import com.stslex.wizard.feature.auth.domain.AuthInteractorImpl
import com.stslex.wizard.feature.auth.navigation.AuthRouter
import com.stslex.wizard.feature.auth.navigation.AuthRouterImpl
import com.stslex.wizard.feature.auth.ui.store.AuthStore
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val featureAuthModule = module {
    factoryOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    factoryOf(::AuthInteractorImpl) { bind<AuthInteractor>() }
    factoryOf(::AuthRouterImpl) { bind<AuthRouter>() }
    storeDefinition {
        AuthStore(
            interactor = get(),
            router = get(),
            dispatcher = get()
        )
    }
}