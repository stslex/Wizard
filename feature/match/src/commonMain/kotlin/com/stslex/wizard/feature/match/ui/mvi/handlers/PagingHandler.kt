package com.stslex.wizard.feature.match.ui.mvi.handlers

import com.stslex.wizard.core.ui.kit.base.paging.toUi
import com.stslex.wizard.core.ui.kit.pager.pager.StorePager
import com.stslex.wizard.core.ui.kit.pager.pager.StorePagerFactory
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.match.di.MatchScope
import com.stslex.wizard.feature.match.domain.interactor.MatchInteractor
import com.stslex.wizard.feature.match.ui.model.MatchUiModel
import com.stslex.wizard.feature.match.ui.model.toUi
import com.stslex.wizard.feature.match.ui.mvi.MatchHandlerStore
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Action
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Event
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchScope::class)
@Scoped
internal class PagingHandler(
    private val pagerFactory: StorePagerFactory,
    private val interactor: MatchInteractor
) : Handler<Action.Paging, MatchHandlerStore> {

    private var _pager: StorePager<MatchUiModel>? = null
    private val pager: StorePager<MatchUiModel>
        get() = requireNotNull(_pager)

    override fun MatchHandlerStore.invoke(action: Action.Paging) {
        when (action) {
            Action.Paging.Init -> actionInit()
            Action.Paging.LoadMore -> actionLoadMore()
            Action.Paging.Refresh -> actionRefresh()
            Action.Paging.Retry -> actionRetry()
        }
    }

    private fun actionLoadMore() {
        pager.load()
    }

    private fun actionRetry() {
        pager.retry()
    }

    private fun actionRefresh() {
        pager.refresh(isForceLoad = true)
    }

    private fun MatchHandlerStore.actionInit() {
        _pager = createPager()

        pager.state.launch { pagerState ->
            updateState { currentState ->
                currentState.copy(
                    paging = pagerState.toUi(currentState.paging.config)
                )
            }
        }

        pager.loadState.launch { loadState ->
            updateState { currentState ->
                currentState.copy(
                    screen = loadState.toUi()
                )
            }
        }

        pager.loadEvents.launch {
            sendEvent(
                Event.ShowSnackbar(Snackbar.Error("error load matches"))
            )
        }

        state
            .map { it.query }
            .distinctUntilChanged()
            .launch(onError = { consume(Action.Common.ShowError(it)) }) {
                if (pager.loadState.value is PagerLoadState.Initial) {
                    pager.initialLoad()
                } else {
                    pager.refresh(isForceLoad = false)
                }
            }
    }

    private fun MatchHandlerStore.createPager(): StorePager<MatchUiModel> = pagerFactory.create(
        request = { page, pageSize ->
            interactor.getMatches(
                uuid = state.value.uuid,
                query = state.value.query,
                page = page,
                pageSize = pageSize
            )
        },
        scope = scope,
        mapper = { it.toUi() },
        config = state.value.paging.config
    )
}