package com.stslex.wizard.feature.favourite.di

import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.favourite.data.repository.FavouriteRepository
import com.stslex.wizard.feature.favourite.data.repository.FavouriteRepositoryImpl
import com.stslex.wizard.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.wizard.feature.favourite.domain.interactor.FavouriteInteractorImpl
import com.stslex.wizard.feature.favourite.navigation.FavouriteRouter
import com.stslex.wizard.feature.favourite.navigation.FavouriteRouterImpl
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore
import org.koin.dsl.module

val featureFavouriteModule = module {

    factory<FavouriteRepository> { FavouriteRepositoryImpl(client = get()) }
    factory<FavouriteInteractor> {
        FavouriteInteractorImpl(repository = get())
    }
    factory<FavouriteRouter> { FavouriteRouterImpl(navigator = get()) }
    storeDefinition {
        FavouriteStore(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
            pagingFactory = get()
        )
    }
}