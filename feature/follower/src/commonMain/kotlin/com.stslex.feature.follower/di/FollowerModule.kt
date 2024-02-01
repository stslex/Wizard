package com.stslex.feature.follower.di

import com.stslex.feature.follower.data.repository.FollowerRepository
import com.stslex.feature.follower.data.repository.FollowerRepositoryImpl
import com.stslex.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.feature.follower.domain.interactor.FollowerInteractorImpl
import com.stslex.feature.follower.navigation.FollowerRouter
import com.stslex.feature.follower.navigation.FollowerRouterImpl
import com.stslex.feature.follower.ui.store.FollowerStore
import org.koin.dsl.module

val featureFollowerModule = module {

    factory<FollowerRepository> {
        FollowerRepositoryImpl(
            client = get()
        )
    }

    factory<FollowerInteractor> {
        FollowerInteractorImpl(
            repository = get()
        )
    }

    factory<FollowerRouter> { FollowerRouterImpl(navigator = get()) }

    factory {
        FollowerStore(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
        )
    }
}