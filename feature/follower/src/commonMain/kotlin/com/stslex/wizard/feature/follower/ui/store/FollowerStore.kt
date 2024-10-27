package com.stslex.wizard.feature.follower.ui.store

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.core.ui.base.mapToAppError
import com.stslex.core.ui.base.paging.toUi
import com.stslex.core.ui.mvi.Store
import com.stslex.core.ui.mvi.StoreComponent.Event.Snackbar
import com.stslex.core.ui.pager.pager.StorePager
import com.stslex.core.ui.pager.pager.StorePagerFactory
import com.stslex.core.ui.pager.states.PagerLoadState
import com.stslex.wizard.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.wizard.feature.follower.navigation.FollowerRouter
import com.stslex.wizard.feature.follower.navigation.FollowerScreenArgs
import com.stslex.wizard.feature.follower.ui.model.FollowerModel
import com.stslex.wizard.feature.follower.ui.model.toUi
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreComponent.Action
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreComponent.Event
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreComponent.Navigation
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreComponent.State
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class FollowerStore(
    private val interactor: FollowerInteractor,
    router: FollowerRouter,
    appDispatcher: AppDispatcher,
    pagerFactory: StorePagerFactory,
) : FollowerStoreComponent, Store<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {

    private val pager: StorePager<FollowerModel> = pagerFactory.create(
        request = { page, pageSize ->
            val currentState = state.value
            when (currentState.type) {
                is FollowerScreenArgs.Follower -> interactor.getFollowers(
                    uuid = currentState.type.uuid,
                    query = currentState.query,
                    page = page,
                    pageSize = pageSize
                )

                is FollowerScreenArgs.Following -> interactor.getFollowing(
                    uuid = currentState.type.uuid,
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

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.Load -> actionLoad()
            is Action.OnUserClick -> actionUserClick(action)
            is Action.QueryChanged -> actionQueryChanged(action)
            Action.Refresh -> actionRefresh()
            Action.Retry -> actionRetry()
        }
    }

    private fun actionInit(action: Action.Init) {
        updateState { state ->
            state.copy(type = action.args)
        }

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
                onError = ::showError
            ) {
                if (pager.loadState.value is PagerLoadState.Initial) {
                    pager.initialLoad()
                } else {
                    pager.refresh(isForceLoad = false)
                }
            }
    }

    private fun actionLoad() {
        pager.load()
    }

    private fun actionUserClick(action: Action.OnUserClick) {
        // todo ("navigate to user")
    }

    private fun actionQueryChanged(action: Action.QueryChanged) {
        updateState { currentState ->
            currentState.copy(
                query = action.query
            )
        }
    }

    private fun actionRefresh() {
        pager.refresh(isForceLoad = true)
    }

    private fun actionRetry() {
        pager.retry()
    }

    private fun showError(error: Throwable) {
        val appError = error.mapToAppError("error logout")
        if (state.value.screen is FollowerScreenState.Content) {
            sendEvent(
                Event.ShowSnackbar(Snackbar.Error(appError.message))
            )
        } else {
            updateState { currentState ->
                currentState.copy(
                    screen = FollowerScreenState.Error(appError)
                )
            }
        }
    }
}
