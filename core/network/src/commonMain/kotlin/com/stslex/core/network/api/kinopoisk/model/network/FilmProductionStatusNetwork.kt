package com.stslex.core.network.api.kinopoisk.model.network

enum class FilmProductionStatusNetwork(val value: String) {
    FILMING("FILMING"),
    PRE_PRODUCTION("PRE_PRODUCTION"),
    COMPLETED("COMPLETED"),
    ANNOUNCED("ANNOUNCED"),
    UNKNOWN("UNKNOWN"),
    POST_PRODUCTION("POST_PRODUCTION");

    companion object {
        fun fromValue(value: String) = FilmProductionStatusNetwork
            .entries
            .firstOrNull { it.value == value }
            ?: UNKNOWN
    }
}