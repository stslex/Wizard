package com.stslex.wizard.core.network.api.kinopoisk.source

import com.stslex.wizard.core.network.api.kinopoisk.model.network.FilmListKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.MovieKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.TrailerKinopoisk

interface KinopoiskNetworkClient {

    suspend fun getFilms(page: Int): FilmListKinopoisk

    suspend fun getFilm(id: String): MovieKinopoisk

    suspend fun getFilmTrailers(id: String): TrailerKinopoisk
}
