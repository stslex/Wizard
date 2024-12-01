package com.stslex.wizard.core.ui.kit.pager.pager

import com.stslex.wizard.core.ui.kit.base.paging.PagingItem
import com.stslex.wizard.core.ui.kit.base.paging.PagingState
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadEvents
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState
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