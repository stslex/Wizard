package com.stslex.core.ui.pager.pager

import com.stslex.core.ui.base.paging.PagingItem
import com.stslex.core.ui.base.paging.PagingState
import com.stslex.core.ui.pager.states.PagerLoadEvents
import com.stslex.core.ui.pager.states.PagerLoadState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface StorePager<out Item : PagingItem> {

    val state: StateFlow<PagingState<Item>>

    val loadState: StateFlow<PagerLoadState>

    val loadEvents: SharedFlow<PagerLoadEvents>

    fun initialLoad()

    fun load()

    fun refresh(isForceLoad: Boolean)

    fun retry()
}