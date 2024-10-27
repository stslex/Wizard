package com.stslex.wizard.feature.film.di

import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.film.data.repository.FilmRepository
import com.stslex.wizard.feature.film.data.repository.FilmRepositoryImpl
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractorImpl
import com.stslex.wizard.feature.film.navigation.FilmRouter
import com.stslex.wizard.feature.film.navigation.FilmRouterImpl
import com.stslex.wizard.feature.film.ui.store.FilmStore
import org.koin.dsl.module

val featureFilmModule = module {
    storeDefinition {
        FilmStore(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
        )
    }
    factory<FilmRouter> {
        FilmRouterImpl(navigator = get())
    }
    factory<FilmInteractor> { FilmInteractorImpl(repository = get()) }
    factory<FilmRepository> {
        FilmRepositoryImpl(
            favouriteDatasource = get(),
            client = get(),
            profileClient = get()
        )
    }
}