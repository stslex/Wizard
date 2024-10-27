package com.stslex.wizard.core.network.api.kinopoisk.source

import com.stslex.wizard.core.network.api.kinopoisk.api.KinopoiskApiClient
import com.stslex.wizard.core.network.api.kinopoisk.model.network.FilmListKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.MovieKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.TrailerKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.response.FilmListResponse
import com.stslex.wizard.core.network.api.kinopoisk.model.response.MovieResponse
import com.stslex.wizard.core.network.api.kinopoisk.model.response.TrailerResponse
import com.stslex.wizard.core.network.api.kinopoisk.model.toNetwork
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KinopoiskNetworkClientImpl(
    private val apiClient: KinopoiskApiClient
) : KinopoiskNetworkClient {

    override suspend fun getFilms(page: Int): FilmListKinopoisk = apiClient
        .request {
            get(ENDPOINT_FILMS) {
                parameter(QUERY_PAGE, page)
            }.body<FilmListResponse>()
        }
        .toNetwork()

    override suspend fun getFilm(id: String): MovieKinopoisk = apiClient
        .request {
            get("$ENDPOINT_FILMS/$id")
                .body<MovieResponse>()
        }
        .toNetwork()

    override suspend fun getFilmTrailers(id: String): TrailerKinopoisk = apiClient
        .request {
            get("$ENDPOINT_FILMS/$id/$ENDPOINT_VIDEOS")
                .body<TrailerResponse>()
        }
        .toNetwork()

    companion object {
        private const val QUERY_PAGE = "page"
        private const val ENDPOINT_FILMS = "films"
        private const val ENDPOINT_VIDEOS = "videos"
    }
}