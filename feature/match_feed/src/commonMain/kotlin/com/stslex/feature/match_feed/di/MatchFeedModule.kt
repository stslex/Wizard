package com.stslex.feature.match_feed.di

import com.stslex.feature.match_feed.data.repository.MatchFeedMockRepositoryImpl
import com.stslex.feature.match_feed.data.repository.MatchFeedRepository
import com.stslex.feature.match_feed.domain.MatchFeedInteractor
import com.stslex.feature.match_feed.domain.MatchFeedInteractorImpl
import com.stslex.feature.match_feed.navigation.MatchFeedRouter
import com.stslex.feature.match_feed.navigation.MatchFeedRouterImpl
import com.stslex.feature.match_feed.ui.store.MatchFeedStore
import com.stslex.feature.match_feed.ui.store.MatchFeedStoreImpl
import org.koin.dsl.module

val featureMatchFeedModule = module {
    factory<MatchFeedRepository> { MatchFeedMockRepositoryImpl(client = get()) }
    factory<MatchFeedInteractor> { MatchFeedInteractorImpl(repository = get()) }
    factory<MatchFeedRouter> { MatchFeedRouterImpl(navigator = get()) }
    factory<MatchFeedStore> {
        MatchFeedStoreImpl(
            interactor = get(),
            appDispatcher = get(),
            router = get()
        )
    }
}