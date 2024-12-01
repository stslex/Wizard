package com.stslex.wizard.feature.film_feed.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.kit.mvi.storeOf
import com.stslex.wizard.feature.film_feed.data.repository.FeedRepository
import com.stslex.wizard.feature.film_feed.data.repository.FeedRepositoryImpl
import com.stslex.wizard.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.wizard.feature.film_feed.domain.interactor.FeedInteractorImpl
import com.stslex.wizard.feature.film_feed.navigation.FeedScreenRouter
import com.stslex.wizard.feature.film_feed.navigation.FeedScreenRouterImpl
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore
import com.stslex.wizard.feature.film_feed.ui.store.FeedStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureFeed : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        storeOf(::FeedStoreImpl) { bind<FeedStore>() }
        factoryOf(::FeedScreenRouterImpl) { bind<FeedScreenRouter>() }
        factoryOf(::FeedInteractorImpl) { bind<FeedInteractor>() }
        factoryOf(::FeedRepositoryImpl) { bind<FeedRepository>() }
    }
}

