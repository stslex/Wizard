package com.stslex.wizard.core.network.clients.film.client

import com.stslex.wizard.core.network.clients.film.model.FilmListNetwork
import com.stslex.wizard.core.network.clients.film.model.FilmTrailerNetwork
import com.stslex.wizard.core.network.clients.film.model.MovieNetwork

interface FilmClient {

    suspend fun getFeedFilms(page: Int, pageSize: Int): FilmListNetwork

    suspend fun getFilm(id: String): MovieNetwork

    suspend fun getFilmTrailers(id: String): List<FilmTrailerNetwork>

    suspend fun likeFilm(id: String)

    suspend fun dislikeFilm(id: String)
}