package com.stslex.core.network.clients.film.client

import com.stslex.core.network.clients.film.model.FilmFeedResponse
import com.stslex.core.network.clients.film.model.FilmResponse
import kotlinx.coroutines.flow.Flow

interface FilmClient {

    suspend fun getFeedFilms(page: Int, pageSize: Int): FilmFeedResponse

    fun getFilm(id: String): Flow<FilmResponse>
}