package com.stslex.wizard.feature.follower.di

import com.stslex.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.follower.data.repository.FollowerRepository
import com.stslex.wizard.feature.follower.data.repository.FollowerRepositoryImpl
import com.stslex.wizard.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.wizard.feature.follower.domain.interactor.FollowerInteractorImpl
import com.stslex.wizard.feature.follower.navigation.FollowerRouter
import com.stslex.wizard.feature.follower.navigation.FollowerRouterImpl
import com.stslex.wizard.feature.follower.ui.store.FollowerStore
import org.koin.dsl.module

val featureFollowerModule = module {
    factory<FollowerRepository> { FollowerRepositoryImpl(client = get()) }
    factory<FollowerInteractor> { FollowerInteractorImpl(repository = get()) }
    factory<FollowerRouter> { FollowerRouterImpl(navigator = get()) }
    storeDefinition {
        FollowerStore(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
            pagerFactory = get(),
        )
    }
}