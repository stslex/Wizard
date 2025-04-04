package com.stslex.wizard.feature.film.di

import com.stslex.wizard.feature.film.data.repository.FilmRepository
import com.stslex.wizard.feature.film.data.repository.FilmRepositoryImpl
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractorImpl
import com.stslex.wizard.feature.film.ui.store.FilmStore
import com.stslex.wizard.feature.film.ui.store.FilmStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

@Module
class ModuleFeatureFilm {

    val module = module(createdAtStart = false) {
        viewModelOf(::FilmStoreImpl) { bind<FilmStore>() }
        factoryOf(::FilmInteractorImpl) { bind<FilmInteractor>() }
        factoryOf(::FilmRepositoryImpl) { bind<FilmRepository>() }
    }
}
