package com.stslex.feature.follower.di

import com.stslex.core.ui.mvi.viewModelDefinition
import com.stslex.feature.follower.data.repository.FollowerRepository
import com.stslex.feature.follower.data.repository.FollowerRepositoryImpl
import com.stslex.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.feature.follower.domain.interactor.FollowerInteractorImpl
import com.stslex.feature.follower.navigation.FollowerRouter
import com.stslex.feature.follower.navigation.FollowerRouterImpl
import com.stslex.feature.follower.ui.store.FollowerStore
import com.stslex.feature.follower.ui.store.FollowerStoreImpl
import org.koin.dsl.module

val featureFollowerModule = module {
    factory<FollowerRepository> { FollowerRepositoryImpl(client = get()) }
    factory<FollowerInteractor> { FollowerInteractorImpl(repository = get()) }
    factory<FollowerRouter> { FollowerRouterImpl(navigator = get()) }
    viewModelDefinition<FollowerStore, FollowerStoreImpl> {
        FollowerStoreImpl(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
            pagerFactory = get(),
        )
    }
}