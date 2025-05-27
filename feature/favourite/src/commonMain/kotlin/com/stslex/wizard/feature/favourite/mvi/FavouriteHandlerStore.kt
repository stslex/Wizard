package com.stslex.wizard.feature.favourite.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Event
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.State

interface FavouriteHandlerStore : FavouriteStore, HandlerStore<State, Action, Event>