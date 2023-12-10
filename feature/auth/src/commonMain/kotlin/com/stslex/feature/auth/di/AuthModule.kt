package com.stslex.feature.auth.di

import com.stslex.feature.auth.data.AuthRepository
import com.stslex.feature.auth.data.AuthRepositoryImpl
import com.stslex.feature.auth.domain.AuthInteractor
import com.stslex.feature.auth.domain.AuthInteractorImpl
import com.stslex.feature.auth.navigation.AuthRouter
import com.stslex.feature.auth.navigation.AuthRouterImpl
import com.stslex.feature.auth.ui.store.AuthStore
import org.koin.dsl.module

val featureAuthModule = module {
    factory<AuthRepository> { AuthRepositoryImpl() }
    factory<AuthInteractor> { AuthInteractorImpl(authRepository = get()) }
    factory<AuthRouter> { AuthRouterImpl(navigator = get()) }
    factory {
        AuthStore(
            interactor = get(),
            router = get(),
            dispatcher = get()
        )
    }
}