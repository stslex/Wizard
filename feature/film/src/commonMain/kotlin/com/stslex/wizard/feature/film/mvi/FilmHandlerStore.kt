package com.stslex.wizard.feature.film.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.film.mvi.FilmStore.Action
import com.stslex.wizard.feature.film.mvi.FilmStore.Event
import com.stslex.wizard.feature.film.mvi.FilmStore.State

interface FilmHandlerStore : HandlerStore<State, Action, Event>, FilmStore