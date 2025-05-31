package com.stslex.wizard.feature.film.domain.interactor

import com.stslex.wizard.feature.film.data.repository.FilmRepository
import com.stslex.wizard.feature.film.di.FilmScope
import com.stslex.wizard.feature.film.domain.model.FilmDomain
import com.stslex.wizard.feature.film.domain.model.toData
import com.stslex.wizard.feature.film.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FilmScope::class)
@Scoped
internal class FilmInteractorImpl(
    private val repository: FilmRepository
) : FilmInteractor {

    override fun getFilm(
        id: String
    ): Flow<FilmDomain> = repository
        .getFilm(id)
        .map { film ->
            film.toDomain()
        }

    override suspend fun likeFilm(film: FilmDomain) {
        repository.likeFilm(film.toData())
    }

    override suspend fun dislikeFilm(id: String) {
        repository.dislikeFilm(id)
    }
}
