package com.stslex.wizard.feature.match.ui.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Action
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Event
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.State

interface MatchHandlerStore : HandlerStore<State, Action, Event>