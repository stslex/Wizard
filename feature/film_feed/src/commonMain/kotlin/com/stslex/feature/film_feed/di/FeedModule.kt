package com.stslex.feature.film_feed.di

import com.stslex.feature.film_feed.data.repository.FeedRepository
import com.stslex.feature.film_feed.data.repository.FeedRepositoryImpl
import com.stslex.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.feature.film_feed.domain.interactor.FeedInteractorImpl
import com.stslex.feature.film_feed.navigation.FeedScreenRouter
import com.stslex.feature.film_feed.navigation.FeedScreenRouterImpl
import com.stslex.feature.film_feed.ui.store.FeedStore
import com.stslex.feature.film_feed.ui.store.FeedStoreImpl
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureFeedModule = module {
    factory<FeedStore> {
        FeedStoreImpl(
            interactor = get(),
            appDispatcher = get(),
            router = get()
        )
    }
    factory<FeedScreenRouter> { FeedScreenRouterImpl(navigator = get()) }
    factory<FeedInteractor> { FeedInteractorImpl(repository = get()) }
    factory<FeedRepository> { FeedRepositoryImpl(client = get()) }
}
