package com.stslex.wizard.feature.profile.ui.store

import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

typealias ProfileStoreProcessor = StoreProcessor<State, Action, Event>