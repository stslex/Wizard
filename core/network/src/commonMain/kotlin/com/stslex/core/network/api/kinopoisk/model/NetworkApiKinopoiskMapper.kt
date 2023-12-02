package com.stslex.core.network.api.kinopoisk.model

import com.stslex.core.network.api.kinopoisk.model.network.FilmItemNetwork
import com.stslex.core.network.api.kinopoisk.model.network.FilmListNetwork
import com.stslex.core.network.api.kinopoisk.model.network.FilmProductionStatusNetwork
import com.stslex.core.network.api.kinopoisk.model.network.FilmTypeNetwork
import com.stslex.core.network.api.kinopoisk.model.network.MovieNetwork
import com.stslex.core.network.api.kinopoisk.model.network.TrailerItemNetwork
import com.stslex.core.network.api.kinopoisk.model.network.TrailerNetwork
import com.stslex.core.network.api.kinopoisk.model.response.FilmItemResponse
import com.stslex.core.network.api.kinopoisk.model.response.FilmListResponse
import com.stslex.core.network.api.kinopoisk.model.response.MovieResponse
import com.stslex.core.network.api.kinopoisk.model.response.TrailerItemResponse
import com.stslex.core.network.api.kinopoisk.model.response.TrailerResponse

fun FilmListResponse.toNetwork(): FilmListNetwork = FilmListNetwork(
    total = total ?: 0,
    totalPages = totalPages ?: 0,
    items = items?.map { it.toNetwork() }.orEmpty()
)

fun FilmItemResponse.toNetwork(): FilmItemNetwork = FilmItemNetwork(
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

fun TrailerResponse.toNetwork(): TrailerNetwork = TrailerNetwork(
    total = total ?: 0,
    items = items?.map { it.toNetwork() }.orEmpty()
)

fun TrailerItemResponse.toNetwork(): TrailerItemNetwork = TrailerItemNetwork(
    url = url.orEmpty(),
    name = name.orEmpty(),
    site = site.orEmpty(),
)

fun MovieResponse.toNetwork(): MovieNetwork = MovieNetwork(
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