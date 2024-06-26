package com.stslex.feature.favourite.di

import com.stslex.feature.favourite.data.repository.FavouriteRepository
import com.stslex.feature.favourite.data.repository.FavouriteRepositoryImpl
import com.stslex.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.feature.favourite.domain.interactor.FavouriteInteractorImpl
import com.stslex.feature.favourite.navigation.FavouriteRouter
import com.stslex.feature.favourite.navigation.FavouriteRouterImpl
import com.stslex.feature.favourite.ui.store.FavouriteStore
import com.stslex.feature.favourite.ui.store.FavouriteStoreImpl
import org.koin.dsl.module

val featureFavouriteModule = module {

    factory<FavouriteRepository> { FavouriteRepositoryImpl(client = get()) }
    factory<FavouriteInteractor> {
        FavouriteInteractorImpl(repository = get())
    }
    factory<FavouriteRouter> { FavouriteRouterImpl(navigator = get()) }
    factory<FavouriteStore> {
        FavouriteStoreImpl(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
            pagingFactory = get()
        )
    }
}