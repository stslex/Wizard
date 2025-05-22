package com.stslex.wizard.feature.auth.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.auth.di.AuthScope
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action
import com.stslex.wizard.feature.auth.mvi.AuthStore.Event
import com.stslex.wizard.feature.auth.mvi.AuthStore.State
import com.stslex.wizard.feature.auth.mvi.handler.AuthClickHandler
import com.stslex.wizard.feature.auth.mvi.handler.AuthComponent
import com.stslex.wizard.feature.auth.mvi.handler.AuthInputHandler
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(AuthScope::class)
@Scoped
@Qualifier(AuthScope::class)
internal class AuthStoreImpl(
    inputHandler: AuthInputHandler,
    clickHandler: AuthClickHandler,
    component: AuthComponent,
    dispatcher: AppDispatcher
) : AuthStore, AuthHandlerStore, BaseStore<State, Action, Event, AuthHandlerStore>(
    name = "AuthStore",
    initialState = State.Companion.INITIAL,
    appDispatcher = dispatcher,
    handlerCreator = { action ->
        when (action) {
            is Action.Click -> clickHandler
            is Action.InputAction -> inputHandler
            is Action.Navigation -> component
        }
    }
)