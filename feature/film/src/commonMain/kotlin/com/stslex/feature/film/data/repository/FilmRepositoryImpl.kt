package com.stslex.feature.film.data.repository

import com.stslex.core.database.sources.source.FavouriteFilmDataSource
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.feature.film.data.model.FilmData
import com.stslex.feature.film.data.model.getTrailer
import com.stslex.feature.film.data.model.toData
import com.stslex.feature.film.data.model.toEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepositoryImpl(
    private val client: FilmClient,
    private val favouriteDatasource: FavouriteFilmDataSource
) : FilmRepository {

    override fun getFilm(id: String): Flow<FilmData> = flow {
        coroutineScope {
            val isFavourite = async {
                favouriteDatasource.getFilm(id) != null
            }
            val trailerUrl = async {
                client.getFilmTrailers(id = id).getTrailer()
            }

            val film = client.getFilm(id = id)
            val result = film.toData(
                isFavourite = isFavourite.await(),
                trailer = trailerUrl.await()
            )
            emit(result)
        }
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
