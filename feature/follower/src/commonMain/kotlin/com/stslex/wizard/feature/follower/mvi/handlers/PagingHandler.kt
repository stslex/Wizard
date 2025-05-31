package com.stslex.wizard.feature.follower.mvi.handlers

import com.stslex.wizard.core.navigation.Config.Follower.FollowerType
import com.stslex.wizard.core.ui.kit.base.paging.toUi
import com.stslex.wizard.core.ui.kit.pager.pager.StorePager
import com.stslex.wizard.core.ui.kit.pager.pager.StorePagerFactory
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.follower.di.FollowerScope
import com.stslex.wizard.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.wizard.feature.follower.mvi.FollowerHandlerStore
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Action
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Event
import com.stslex.wizard.feature.follower.mvi.PagerLoadStateMapper.toUi
import com.stslex.wizard.feature.follower.ui.model.FollowerModel
import com.stslex.wizard.feature.follower.ui.model.toUi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FollowerScope::class)
@Scoped
internal class PagingHandler(
    private val interactor: FollowerInteractor,
    private val pagerFactory: StorePagerFactory,
) : Handler<Action.Paging, FollowerHandlerStore> {

    private var _pager: StorePager<FollowerModel>? = null
    private val pager: StorePager<FollowerModel>
        get() = requireNotNull(_pager)

    override fun FollowerHandlerStore.invoke(action: Action.Paging) {
        when (action) {
            Action.Paging.Init -> actionInit()
            Action.Paging.Load -> actionLoad()
            Action.Paging.Refresh -> actionRefresh()
            Action.Paging.Retry -> actionRetry()
        }
    }

    private fun actionLoad() {
        pager.load()
    }

    private fun actionRefresh() {
        pager.refresh(isForceLoad = true)
    }

    private fun actionRetry() {
        pager.retry()
    }

    private fun FollowerHandlerStore.actionInit() {
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
            .launch(
                onError = { consume(Action.Common.ShowError(it)) }
            ) {
                if (pager.loadState.value is PagerLoadState.Initial) {
                    pager.initialLoad()
                } else {
                    pager.refresh(isForceLoad = false)
                }
            }
    }

    private fun FollowerHandlerStore.createPager(): StorePager<FollowerModel> = pagerFactory.create(
        request = { page, pageSize ->
            val currentState = state.value
            when (currentState.type) {
                FollowerType.FOLLOWER -> interactor.getFollowers(
                    uuid = currentState.uuid,
                    query = currentState.query,
                    page = page,
                    pageSize = pageSize
                )

                FollowerType.FOLLOWING -> interactor.getFollowing(
                    uuid = currentState.uuid,
                    query = currentState.query,
                    page = page,
                    pageSize = pageSize
                )
            }
        },
        scope = scope,
        mapper = { it.toUi() },
        config = state.value.paging.config
    )
}