package com.stslex.feature.film.data.repository

import com.stslex.core.database.sources.source.FavouriteFilmDataSource
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.feature.film.data.model.FilmData
import com.stslex.feature.film.data.model.toData
import com.stslex.feature.film.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepositoryImpl(
    private val client: FilmClient,
    private val favouriteDatasource: FavouriteFilmDataSource
) : FilmRepository {

    override fun getFilm(id: String): Flow<FilmData> = client.getFilm(id = id)
        .map {
            it.toData().copy(
                isFavorite = favouriteDatasource.getFilm(id) != null
            )
        }

    override suspend fun likeFilm(filmData: FilmData) {
        favouriteDatasource.likeFilm(filmData.toEntity())
        client.likeFilm(filmData.id)
    }

    override suspend fun dislikeFilm(id: String) {
        favouriteDatasource.dislikeFilm(id)
        client.dislikeFilm(id)
    }
}
