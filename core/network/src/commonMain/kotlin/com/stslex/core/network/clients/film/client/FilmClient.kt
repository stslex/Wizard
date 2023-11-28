package com.stslex.core.network.clients.film.client

import com.stslex.core.network.clients.film.model.FilmFeedResponse
import com.stslex.core.network.clients.film.model.FilmResponse

interface FilmClient {

    suspend fun getFeedFilms(page: Int, pageSize: Int): FilmFeedResponse

    suspend fun getFilm(id: String): FilmResponse
}