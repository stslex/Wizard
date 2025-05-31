package com.stslex.wizard.feature.profile.ui.mvi.store

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileStore.State

interface ProfileHandlerStore : ProfileStore, HandlerStore<State, Action, Event>