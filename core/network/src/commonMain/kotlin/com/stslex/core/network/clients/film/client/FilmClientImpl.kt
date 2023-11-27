package com.stslex.core.network.clients.film.client

import com.stslex.core.network.client.NetworkClient
import com.stslex.core.network.clients.film.model.FilmFeedResponse
import com.stslex.core.network.clients.film.model.FilmResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmClientImpl(
    private val client: NetworkClient
) : FilmClient {

    override suspend fun getFeedFilms(
        page: Int, pageSize: Int
    ): FilmFeedResponse = client.request {
        get("feed") {
            parameter("page", page)
            parameter("pageSize", pageSize)
        }.body()
    }

    override fun getFilm(
        id: String
    ): Flow<FilmResponse> = flow {
        val film: FilmResponse = client.request {
            get("feed") {
                parameter("id", id)
            }.body()
        }
        emit(film)
    }
}