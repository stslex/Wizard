package com.stslex.wizard.core.network.api.clients.film.model

enum class TrailerSiteType(val value: String) {
    YOUTUBE("YOUTUBE"),
    KINOPOISK_WIDGET("KINOPOISK_WIDGET"),
    YANDEX_DISK("YANDEX_DISK"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun getByValue(
            value: String
        ): TrailerSiteType = entries.firstOrNull { it.value == value } ?: UNKNOWN
    }
}