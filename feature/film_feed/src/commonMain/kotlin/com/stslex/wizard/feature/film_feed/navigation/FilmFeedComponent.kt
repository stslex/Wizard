package com.stslex.wizard.feature.film_feed.navigation

import com.stslex.wizard.core.navigation.v2.Component

interface FilmFeedComponent : Component {

    fun openFilm(id: String)
}