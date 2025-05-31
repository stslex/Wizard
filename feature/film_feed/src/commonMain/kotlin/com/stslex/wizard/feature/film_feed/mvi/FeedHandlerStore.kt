package com.stslex.wizard.feature.film_feed.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore

interface FeedHandlerStore : FeedStore, HandlerStore<FeedStore.State, FeedStore.Action, FeedStore.Event>