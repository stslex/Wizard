package com.stslex.wizard.feature.match.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.match.data.repository.MatchRepository
import com.stslex.wizard.feature.match.data.repository.MatchRepositoryImpl
import com.stslex.wizard.feature.match.domain.interactor.MatchInteractor
import com.stslex.wizard.feature.match.domain.interactor.MatchInteractorImpl
import com.stslex.wizard.feature.match.navigation.MatchRouter
import com.stslex.wizard.feature.match.navigation.MatchRouterImpl
import com.stslex.wizard.feature.match.ui.store.MatchStore
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureMatch : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::MatchRepositoryImpl) { bind<MatchRepository>() }
        factoryOf(::MatchInteractorImpl) { bind<MatchInteractor>() }
        factoryOf(::MatchRouterImpl) { bind<MatchRouter>() }
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
}
