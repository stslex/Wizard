package com.stslex.feature.profile.di

import com.stslex.feature.profile.data.repository.ProfileRepository
import com.stslex.feature.profile.data.repository.ProfileRepositoryImpl
import com.stslex.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.feature.profile.domain.interactor.ProfileInteractorImpl
import com.stslex.feature.profile.navigation.ProfileRouter
import com.stslex.feature.profile.navigation.ProfileRouterImpl
import com.stslex.feature.profile.ui.store.ProfileStore
import org.koin.dsl.module

val featureProfileModule = module {
    factory {
        ProfileStore(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
        )
    }
    factory<ProfileRouter> { ProfileRouterImpl(navigator = get()) }
    factory<ProfileRepository> { ProfileRepositoryImpl(client = get()) }
    factory<ProfileInteractor> { ProfileInteractorImpl(repository = get()) }
}