package com.stslex.feature.film.data.repository

import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.feature.film.data.model.FilmData
import com.stslex.feature.film.data.model.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepositoryImpl(
    private val client: FilmClient
) : FilmRepository {

    override fun getFilm(id: String): Flow<FilmData> = flow {
        val film = client.getFilm(id = id).toData()
        emit(film)
    }
}