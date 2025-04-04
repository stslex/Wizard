package com.stslex.wizard.feature.film_feed.di

import com.stslex.wizard.feature.film_feed.data.repository.FeedRepository
import com.stslex.wizard.feature.film_feed.data.repository.FeedRepositoryImpl
import com.stslex.wizard.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.wizard.feature.film_feed.domain.interactor.FeedInteractorImpl
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore
import com.stslex.wizard.feature.film_feed.ui.store.FeedStoreImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.core.annotation.Module as ModuleQualifier

@ModuleQualifier
class ModuleFeatureFilmFeed {

    val module = module(createdAtStart = false) {
        viewModelOf(::FeedStoreImpl) { bind<FeedStore>() }
        factoryOf(::FeedInteractorImpl) { bind<FeedInteractor>() }
        factoryOf(::FeedRepositoryImpl) { bind<FeedRepository>() }
    }
}

