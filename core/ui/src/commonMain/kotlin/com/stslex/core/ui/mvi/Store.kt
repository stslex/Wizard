package com.stslex.core.ui.mvi

interface Store {

    interface State

    interface Event

    interface Navigation

    interface Action {
        interface RepeatLastAction : Action
    }
}
