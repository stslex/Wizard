package com.stslex.wizard.feature.auth.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action
import com.stslex.wizard.feature.auth.mvi.AuthStore.Event
import com.stslex.wizard.feature.auth.mvi.AuthStore.State

interface AuthHandlerStore : AuthStore, HandlerStore<State, Action, Event>