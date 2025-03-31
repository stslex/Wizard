package com.stslex.wizard.feature.film.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.store_di.storeOf
import com.stslex.wizard.feature.film.data.repository.FilmRepository
import com.stslex.wizard.feature.film.data.repository.FilmRepositoryImpl
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractorImpl
import com.stslex.wizard.feature.film.navigation.FilmRouter
import com.stslex.wizard.feature.film.navigation.FilmRouterImpl
import com.stslex.wizard.feature.film.ui.store.FilmStore
import com.stslex.wizard.feature.film.ui.store.FilmStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureFilm : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        viewModelOf(::FilmStoreImpl) { bind<FilmStore>() }
        storeOf(::FilmStoreImpl) { bind<FilmStore>() }
        factoryOf(::FilmRouterImpl) { bind<FilmRouter>() }
        factoryOf(::FilmInteractorImpl) { bind<FilmInteractor>() }
        factoryOf(::FilmRepositoryImpl) { bind<FilmRepository>() }
    }
}
