package com.stslex.wizard.feature.favourite.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.store_di.storeOf
import com.stslex.wizard.feature.favourite.data.repository.FavouriteRepository
import com.stslex.wizard.feature.favourite.data.repository.FavouriteRepositoryImpl
import com.stslex.wizard.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.wizard.feature.favourite.domain.interactor.FavouriteInteractorImpl
import com.stslex.wizard.feature.favourite.navigation.FavouriteRouter
import com.stslex.wizard.feature.favourite.navigation.FavouriteRouterImpl
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureFavourite : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::FavouriteRepositoryImpl) { bind<FavouriteRepository>() }
        factoryOf(::FavouriteInteractorImpl) { bind<FavouriteInteractor>() }
        factoryOf(::FavouriteRouterImpl) { bind<FavouriteRouter>() }
        storeOf(::FavouriteStoreImpl) { bind<FavouriteStore>() }
    }
}
