package com.stslex.wizard.feature.match_feed.ui.mvi

import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Event
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.State

interface MatchFeedHandlerStore : HandlerStore<State, Action, Event>