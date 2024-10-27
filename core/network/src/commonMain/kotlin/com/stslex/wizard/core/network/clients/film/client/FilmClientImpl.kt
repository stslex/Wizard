package com.stslex.wizard.core.network.clients.film.client

import com.stslex.core.network.api.base.NetworkClient
import com.stslex.wizard.core.network.api.kinopoisk.source.KinopoiskNetworkClient
import com.stslex.core.network.clients.film.model.FilmListNetwork
import com.stslex.wizard.core.network.clients.film.model.FilmTrailerNetwork
import com.stslex.wizard.core.network.clients.film.model.MovieNetwork
import com.stslex.core.network.clients.film.model.toNetwork

class FilmClientImpl(
    private val client: NetworkClient,
    private val kinopoiskClient: KinopoiskNetworkClient
) : FilmClient {

    override suspend fun getFeedFilms(
        page: Int, pageSize: Int
    ): FilmListNetwork = kinopoiskClient
        .getFilms(page)
        .toNetwork()

    override suspend fun getFilm(id: String): MovieNetwork = kinopoiskClient
        .getFilm(id)
        .toNetwork()

    override suspend fun getFilmTrailers(
        id: String
    ): List<FilmTrailerNetwork> = kinopoiskClient
        .getFilmTrailers(id)
        .toNetwork()

    override suspend fun likeFilm(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun dislikeFilm(id: String) {
        TODO("Not yet implemented")
    }
}