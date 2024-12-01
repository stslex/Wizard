package com.stslex.wizard.feature.film.data.repository

import com.stslex.wizard.core.database.sources.source.FavouriteFilmDataSource
import com.stslex.wizard.core.network.api.clients.film.client.FilmClient
import com.stslex.wizard.core.network.api.clients.profile.client.ProfileClient
import com.stslex.wizard.feature.film.data.model.FilmData
import com.stslex.wizard.feature.film.data.model.getTrailer
import com.stslex.wizard.feature.film.data.model.toData
import com.stslex.wizard.feature.film.data.model.toEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepositoryImpl(
    private val client: FilmClient,
    private val profileClient: ProfileClient,
    private val favouriteDatasource: FavouriteFilmDataSource
) : FilmRepository {

    override fun getFilm(id: String): Flow<FilmData> = flow {
        coroutineScope {
            /*TODO: Implement caching for favourite films in the local database
            *  with extra remote saving*/
            val isFavouriteLocal = async {
                favouriteDatasource.getFilm(id) != null
            }

            val isFavouriteRemote = async {
                try {
                    profileClient.isFavourite(favouriteUuid = id).result
                } catch (e: Throwable) {
                    false
                }
            }

            val trailerUrl = async {
                client.getFilmTrailers(id = id).getTrailer()
            }


            val film = client.getFilm(id = id)
            val result = film.toData(
                isFavourite = isFavouriteRemote.await(),
                trailer = trailerUrl.await()
            )
            emit(result)
        }
    }

    override suspend fun likeFilm(filmData: FilmData) {
        favouriteDatasource.likeFilm(filmData.toEntity())
//        client.likeFilm(filmData.id)
        profileClient.addFavourite(
            uuid = filmData.id,
            title = filmData.title
        )
    }

    override suspend fun dislikeFilm(id: String) {
        favouriteDatasource.dislikeFilm(id)
//        client.dislikeFilm(id)
        profileClient.removeFavourite(id)
    }
}
