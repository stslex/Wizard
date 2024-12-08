package com.stslex.wizard.feature.film.navigation

import com.stslex.wizard.core.ui.mvi.Router
import com.stslex.wizard.feature.film.ui.store.FilmStore

interface FilmRouter : Router<FilmStore.Action.Navigation>
