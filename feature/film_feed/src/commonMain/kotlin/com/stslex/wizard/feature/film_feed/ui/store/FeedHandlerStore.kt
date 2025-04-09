package com.stslex.wizard.feature.film_feed.ui.store

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.State

interface FeedHandlerStore : FeedStore, HandlerStore<State, Action, Event>