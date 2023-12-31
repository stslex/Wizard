package com.stslex.feature.film.domain.interactor

import com.stslex.feature.film.data.repository.FilmRepository
import com.stslex.feature.film.domain.model.FilmDomain
import com.stslex.feature.film.domain.model.toData
import com.stslex.feature.film.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmInteractorImpl(
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
