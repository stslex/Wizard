package com.stslex.wizard.core.network.api.kinopoisk.model

import com.stslex.core.network.api.kinopoisk.model.network.FilmItemKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.FilmListKinopoisk
import com.stslex.core.network.api.kinopoisk.model.network.FilmProductionStatusNetwork
import com.stslex.core.network.api.kinopoisk.model.network.FilmTypeNetwork
import com.stslex.wizard.core.network.api.kinopoisk.model.network.MovieKinopoisk
import com.stslex.core.network.api.kinopoisk.model.network.TrailerItemKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.TrailerKinopoisk
import com.stslex.core.network.api.kinopoisk.model.response.FilmItemResponse
import com.stslex.wizard.core.network.api.kinopoisk.model.response.FilmListResponse
import com.stslex.wizard.core.network.api.kinopoisk.model.response.MovieResponse
import com.stslex.wizard.core.network.api.kinopoisk.model.response.TrailerItemResponse
import com.stslex.wizard.core.network.api.kinopoisk.model.response.TrailerResponse
import com.stslex.core.network.clients.film.model.TrailerSiteType

fun FilmListResponse.toNetwork(): FilmListKinopoisk =
    FilmListKinopoisk(
        total = total ?: 0,
        totalPages = totalPages ?: 0,
        items = items?.map { it.toNetwork() }.orEmpty()
    )

fun FilmItemResponse.toNetwork(): FilmItemKinopoisk = FilmItemKinopoisk(
    kinopoiskId = kinopoiskId,
    imdbId = imdbId.orEmpty(),
    nameRu = nameRu.orEmpty(),
    nameEn = nameEn.orEmpty(),
    nameOriginal = nameOriginal.orEmpty(),
    countries = countries?.map { it.country }.orEmpty(),
    genres = genres?.map { it.genre }.orEmpty(),
    ratingKinopoisk = ratingKinopoisk,
    ratingImdb = ratingImdb,
    year = year,
    posterUrl = posterUrl.orEmpty(),
    posterUrlPreview = posterUrlPreview.orEmpty(),
    type = FilmTypeNetwork.getType(type.orEmpty())
)

fun TrailerResponse.toNetwork(): TrailerKinopoisk =
    TrailerKinopoisk(
        total = total ?: 0,
        items = items?.map { it.toNetwork() }.orEmpty()
    )

fun TrailerItemResponse.toNetwork(): TrailerItemKinopoisk = TrailerItemKinopoisk(
    url = url.orEmpty(),
    name = name.orEmpty(),
    site = TrailerSiteType.getByValue(site.orEmpty()),
)

fun MovieResponse.toNetwork(): MovieKinopoisk =
    MovieKinopoisk(
        kinopoiskId = kinopoiskId,
        kinopoiskHDId = kinopoiskHDId.orEmpty(),
        imdbId = imdbId.orEmpty(),
        nameRu = nameRu.orEmpty(),
        nameEn = nameEn.orEmpty(),
        nameOriginal = nameOriginal.orEmpty(),
        posterUrl = posterUrl.orEmpty(),
        posterUrlPreview = posterUrlPreview.orEmpty(),
        coverUrl = coverUrl.orEmpty(),
        logoUrl = logoUrl.orEmpty(),
        reviewsCount = reviewsCount,
        ratingGoodReview = ratingGoodReview,
        ratingGoodReviewVoteCount = ratingGoodReviewVoteCount,
        ratingKinopoisk = ratingKinopoisk,
        ratingKinopoiskVoteCount = ratingKinopoiskVoteCount,
        ratingImdb = ratingImdb,
        ratingImdbVoteCount = ratingImdbVoteCount,
        ratingFilmCritics = ratingFilmCritics,
        ratingFilmCriticsVoteCount = ratingFilmCriticsVoteCount,
        ratingAwait = ratingAwait,
        ratingAwaitCount = ratingAwaitCount,
        ratingRfCritics = ratingRfCritics,
        ratingRfCriticsVoteCount = ratingRfCriticsVoteCount,
        webUrl = webUrl.orEmpty(),
        year = year,
        filmLength = filmLength,
        slogan = slogan.orEmpty(),
        description = description,
        shortDescription = shortDescription.orEmpty(),
        editorAnnotation = editorAnnotation.orEmpty(),
        isTicketsAvailable = isTicketsAvailable ?: false,
        productionStatus = FilmProductionStatusNetwork.getType(productionStatus.orEmpty()),
        type = type,
        ratingMpaa = ratingMpaa,
        ratingAgeLimits = ratingAgeLimits,
        hasImax = hasImax ?: false,
        has3D = has3D ?: false,
        lastSync = lastSync,
        countries = countries?.map { it.country }.orEmpty(),
        genres = genres?.map { it.genre }.orEmpty(),
        startYear = startYear,
        endYear = endYear,
        isSerial = serial ?: false,
        isShortFilm = shortFilm ?: false,
        isCompleted = completed ?: false
    )