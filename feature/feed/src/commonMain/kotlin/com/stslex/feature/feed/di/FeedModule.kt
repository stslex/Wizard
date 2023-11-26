package com.stslex.feature.feed.di

import com.stslex.core.ui.base.viewModelDefinition
import com.stslex.feature.feed.data.repository.FeedRepository
import com.stslex.feature.feed.data.repository.MockFeedRepositoryImpl
import com.stslex.feature.feed.domain.interactor.FeedInteractor
import com.stslex.feature.feed.domain.interactor.FeedInteractorImpl
import com.stslex.feature.feed.navigation.FeedScreenRouter
import com.stslex.feature.feed.navigation.FeedScreenRouterImpl
import com.stslex.feature.feed.ui.store.FeedScreenStore
import org.koin.dsl.module

val feedModule = module {
    viewModelDefinition {
        FeedScreenStore(
            interactor = get(),
            appDispatcher = get(),
            router = get()
        )
    }

    factory<FeedScreenRouter> { FeedScreenRouterImpl(get()) }
    factory<FeedInteractor> { FeedInteractorImpl(get()) }
    factory<FeedRepository> { MockFeedRepositoryImpl() }
}
