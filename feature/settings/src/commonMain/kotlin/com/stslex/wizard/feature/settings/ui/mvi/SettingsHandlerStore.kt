package com.stslex.wizard.feature.settings.ui.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.State

interface SettingsHandlerStore : SettingsStore, HandlerStore<State, Action, Event>