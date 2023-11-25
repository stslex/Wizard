package com.stslex.feature.feed.di

import com.stslex.core.ui.base.viewModelDefinition
import com.stslex.feature.feed.navigation.FeedScreenRouter
import com.stslex.feature.feed.navigation.FeedScreenRouterImpl
import com.stslex.feature.feed.ui.store.FeedScreenStore
import org.koin.dsl.module

val feedModule = module {
    viewModelDefinition { FeedScreenStore(get(), get()) }
    factory<FeedScreenRouter> {
        FeedScreenRouterImpl(get())
    }
}
