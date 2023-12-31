package com.stslex.feature.film_feed.di

import com.stslex.feature.film_feed.data.repository.FeedRepository
import com.stslex.feature.film_feed.data.repository.FeedRepositoryImpl
import com.stslex.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.feature.film_feed.domain.interactor.FeedInteractorImpl
import com.stslex.feature.film_feed.navigation.FeedScreenRouter
import com.stslex.feature.film_feed.navigation.FeedScreenRouterImpl
import com.stslex.feature.film_feed.ui.store.FeedScreenStore
import org.koin.dsl.module

val featureFeedModule = module {
    factory {
        FeedScreenStore(
            interactor = get(),
            appDispatcher = get(),
            router = get()
        )
    }
    factory<FeedScreenRouter> { FeedScreenRouterImpl(navigator = get()) }
    factory<FeedInteractor> { FeedInteractorImpl(repository = get()) }
    factory<FeedRepository> { FeedRepositoryImpl(client = get()) }
}
