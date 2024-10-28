package com.stslex.wizard.feature.film.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.film.data.repository.FilmRepository
import com.stslex.wizard.feature.film.data.repository.FilmRepositoryImpl
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractorImpl
import com.stslex.wizard.feature.film.navigation.FilmRouter
import com.stslex.wizard.feature.film.navigation.FilmRouterImpl
import com.stslex.wizard.feature.film.ui.store.FilmStore
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureFilm : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        storeDefinition {
            FilmStore(
                interactor = get(),
                appDispatcher = get(),
                router = get(),
            )
        }
        factoryOf(::FilmRouterImpl) { bind<FilmRouter>() }
        factoryOf(::FilmInteractorImpl) { bind<FilmInteractor>() }
        factoryOf(::FilmRepositoryImpl) { bind<FilmRepository>() }
    }
}
