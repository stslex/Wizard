package com.stslex.feature.feed.ui.store

import com.stslex.core.ui.mvi.Store

interface FeedScreenStoreComponent : Store {

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

    interface Navigation : Store.Navigation
}