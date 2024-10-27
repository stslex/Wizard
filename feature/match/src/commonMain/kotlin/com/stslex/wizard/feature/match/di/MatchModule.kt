package com.stslex.wizard.feature.match.di

import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.match.data.repository.MatchRepository
import com.stslex.wizard.feature.match.data.repository.MatchRepositoryImpl
import com.stslex.wizard.feature.match.domain.interactor.MatchInteractor
import com.stslex.wizard.feature.match.domain.interactor.MatchInteractorImpl
import com.stslex.wizard.feature.match.navigation.MatchRouter
import com.stslex.wizard.feature.match.navigation.MatchRouterImpl
import com.stslex.wizard.feature.match.ui.store.MatchStore
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