package com.stslex.feature.film.di

import com.stslex.feature.film.data.repository.FilmRepository
import com.stslex.feature.film.data.repository.FilmRepositoryImpl
import com.stslex.feature.film.domain.interactor.FilmInteractor
import com.stslex.feature.film.domain.interactor.FilmInteractorImpl
import com.stslex.feature.film.navigation.FilmRouter
import com.stslex.feature.film.navigation.FilmRouterImpl
import com.stslex.feature.film.ui.store.FilmStore
import org.koin.dsl.module

val featureFilmModule = module {
    factory {
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