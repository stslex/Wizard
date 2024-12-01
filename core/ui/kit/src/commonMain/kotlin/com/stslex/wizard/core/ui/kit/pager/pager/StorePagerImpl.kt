package com.stslex.wizard.core.ui.kit.pager.pager

import com.stslex.wizard.core.core.paging.PagingCoreData.Companion.DEFAULT_PAGE
import com.stslex.wizard.core.core.paging.PagingCoreItem
import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.ui.kit.base.mapToAppError
import com.stslex.wizard.core.ui.kit.base.paging.PagingConfig
import com.stslex.wizard.core.ui.kit.base.paging.PagingItem
import com.stslex.wizard.core.ui.kit.base.paging.PagingState
import com.stslex.wizard.core.ui.kit.base.paging.pagingMap
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadEvents
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState
import com.stslex.wizard.core.ui.kit.pager.mapper.PagingMapper
import com.stslex.wizard.core.ui.kit.pager.paging_worker.PagingRequestType
import com.stslex.wizard.core.ui.kit.pager.paging_worker.PagingWorker
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StorePagerImpl<out T : PagingItem, in R : PagingCoreItem>(
    private val pagingWorker: PagingWorker,
    private val request: suspend (page: Int, pageSize: Int) -> PagingResponse<R>,
    private val mapper: PagingMapper<R, T>,
    pagingConfig: PagingConfig,
) : StorePager<T> {

    private val _state = MutableStateFlow<PagingState<T>>(PagingState.default(pagingConfig))
    override val state = _state.asStateFlow()

    private val _loadState = MutableStateFlow<PagerLoadState>(PagerLoadState.Initial)
    override val loadState = _loadState.asStateFlow()

    private val _loadEvents = MutableSharedFlow<PagerLoadEvents>()
    override val loadEvents = _loadEvents.asSharedFlow()

    private var loadJob: Job? = null

    override fun initialLoad() {
        _state.update { currentState ->
            currentState.copy(
                hasMore = true
            )
        }
        if (state.value.result.isEmpty()) {
            requestItems(isForceLoad = true)
        }
    }

    override fun load() {
        if (
            state.value.hasMore.not() ||
            loadState.value !is PagerLoadState.Data
        ) {
            return
        }
        _loadState.value = PagerLoadState.Loading
        requestItems(isForceLoad = false)
    }

    override fun refresh(isForceLoad: Boolean) {
        _loadState.value = PagerLoadState.Refresh
        _state.update { currentState ->
            currentState.copy(
                page = DEFAULT_PAGE,
            )
        }
        requestItems(isForceLoad = isForceLoad)
    }

    override fun retry() {
        if (
            loadState.value !is PagerLoadState.Error ||
            loadState.value is PagerLoadState.Initial
        ) {
            return
        }
        _loadState.value = PagerLoadState.Retry
        requestItems(isForceLoad = false)
    }

    private fun requestItems(
        isForceLoad: Boolean,
        requestType: PagingRequestType = PagingRequestType.DEFAULT
    ) {
        if (loadJob?.isActive == true && isForceLoad.not()) {
            return
        }
        pagingWorker.launch(
            requestType = requestType,
            action = {
                val page = state.value.page
                val pageSize = state.value.pageSize
                request(page, pageSize)
            },
            onSuccess = { result ->
                val newPagingState = result.pagingMap(mapper::invoke)
                if (
                    newPagingState.result.isEmpty() &&
                    (state.value.page == DEFAULT_PAGE || state.value.result.isEmpty())
                ) {
                    _state.value = newPagingState
                    _loadState.value = PagerLoadState.Empty
                    return@launch
                }
                val newItems = if (state.value.page == DEFAULT_PAGE) {
                    newPagingState.result
                } else {
                    (state.value.result + newPagingState.result).toImmutableList()
                }
                _state.value = newPagingState.copy(
                    result = newItems
                )
                _loadState.value = PagerLoadState.Data
            },
            onError = { error ->
                val appError = error.mapToAppError("error load matches")
                if (
                    loadState.value is PagerLoadState.Data ||
                    loadState.value is PagerLoadState.Loading ||
                    loadState.value is PagerLoadState.Refresh
                ) {
                    _loadEvents.emit(PagerLoadEvents.Error(appError))
                } else {
                    _loadState.value = PagerLoadState.Error(appError)
                }
            }
        )
    }
}