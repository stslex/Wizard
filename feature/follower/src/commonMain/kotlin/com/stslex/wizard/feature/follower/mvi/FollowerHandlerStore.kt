package com.stslex.wizard.feature.follower.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Action
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Event
import com.stslex.wizard.feature.follower.mvi.FollowerStore.State

interface FollowerHandlerStore : HandlerStore<State, Action, Event>, FollowerStore