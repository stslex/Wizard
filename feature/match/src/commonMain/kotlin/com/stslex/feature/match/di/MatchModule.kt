package com.stslex.feature.match.di

import com.stslex.core.ui.mvi.storeDefinition
import com.stslex.feature.match.data.repository.MatchRepository
import com.stslex.feature.match.data.repository.MatchRepositoryImpl
import com.stslex.feature.match.domain.interactor.MatchInteractor
import com.stslex.feature.match.domain.interactor.MatchInteractorImpl
import com.stslex.feature.match.navigation.MatchRouter
import com.stslex.feature.match.navigation.MatchRouterImpl
import com.stslex.feature.match.ui.store.MatchStore
import org.koin.dsl.module

val featureMatchModule = module {
    factory<MatchRepository> {
        MatchRepositoryImpl(
            client = get(),
            userStore = get()
        )
    }
    factory<MatchInteractor> {
        MatchInteractorImpl(
            repository = get(),
            authController = get()
        )
    }
    factory<MatchRouter> { MatchRouterImpl(navigator = get()) }
    storeDefinition {
        MatchStore(
            interactor = get(),
            router = get(),
            appDispatcher = get(),
            userStore = get(),
            pagerFactory = get()
        )
    }
}