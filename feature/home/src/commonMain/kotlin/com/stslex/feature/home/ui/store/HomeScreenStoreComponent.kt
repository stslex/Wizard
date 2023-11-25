package com.stslex.feature.home.ui.store

import com.stslex.core.ui.mvi.Store

interface HomeScreenStoreComponent : Store {

    data class State(
        val text: String
    ) : Store.State {

        companion object {
            val INITIAL = State(text = "Hello, World!")
        }
    }

    interface Event : Store.Event

    interface Action : Store.Action {

        object OnClick : Action
    }

    interface Navigation : Store.Navigation {

        data class SecondScreen(val text: String) : Navigation
    }
}