package com.stslex.wizard.core.network.api.kinopoisk.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    @SerialName("country")
    val country: String,
)

