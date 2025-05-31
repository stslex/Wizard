package com.stslex.wizard.feature.settings.ui.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.settings.di.SettingsScope
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.State
import com.stslex.wizard.feature.settings.ui.mvi.handlers.ClickHandler
import com.stslex.wizard.feature.settings.ui.mvi.handlers.SettingsComponent
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(SettingsScope::class)
@Scoped
@Qualifier(SettingsScope::class)
internal class SettingsStoreImpl(
    appDispatcher: AppDispatcher,
    component: SettingsComponent,
    clickHandler: ClickHandler
) : SettingsHandlerStore, BaseStore<State, Action, Event, SettingsHandlerStore>(
    name = "SettingsStore",
    initialState = State.INITIAL,
    appDispatcher = appDispatcher,
    handlerCreator = { action ->
        when (action) {
            is Action.Navigation -> component
            is Action.Click -> clickHandler
        }
    },
)