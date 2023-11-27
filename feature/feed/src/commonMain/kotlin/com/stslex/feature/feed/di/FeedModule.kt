package com.stslex.feature.feed.di

import com.stslex.feature.feed.data.repository.FeedRepository
import com.stslex.feature.feed.data.repository.FeedRepositoryImpl
import com.stslex.feature.feed.domain.interactor.FeedInteractor
import com.stslex.feature.feed.domain.interactor.FeedInteractorImpl
import com.stslex.feature.feed.navigation.FeedScreenRouter
import com.stslex.feature.feed.navigation.FeedScreenRouterImpl
import com.stslex.feature.feed.ui.store.FeedScreenStore
import org.koin.dsl.module

val feedModule = module {
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
