package com.stslex.wizard.core.network.api.api.kinopoisk.model.network

enum class FilmTypeNetwork(val value: String) {
    FILM("FILM"),
    TV_SHOW("TV_SHOW"),
    VIDEO("VIDEO"),
    MINI_SERIES("MINI_SERIES"),
    TV_SERIES("TV_SERIES"),
    UNKNOWN("TV_SERIES");

    companion object {
        fun getType(value: String): FilmTypeNetwork =
            FilmTypeNetwork.entries
                .firstOrNull { it.value == value }
                ?: UNKNOWN
    }
}