package com.stslex.feature.follower.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.feature.follower.navigation.FollowerRouter
import com.stslex.feature.follower.navigation.FollowerScreenArgs
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.Action
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.Event
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.Navigation
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.State
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.State.Companion.DEFAULT_PAGE
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job

class FollowerStore(
    private val interactor: FollowerInteractor,
    router: FollowerRouter,
    appDispatcher: AppDispatcher,
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {

    private var loadingJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.LoadMore -> actionLoadMore()
        }
    }

    private fun actionLoadMore() {
        loadNextItems()
    }

    private fun actionInit(action: Action.Init) {
        updateState { state ->
            state.copy(
                uuid = action.args.uuid,
                type = action.args,
                screen = FollowerScreenState.Shimmer
            )
        }
        loadNextItems()
    }

    private fun loadNextItems() {
        if (loadingJob?.isActive == true) return

        val currentState = state.value

        val loadingScreen = if (
            currentState.screen is FollowerScreenState.Content &&
            currentState.data.isNotEmpty()
        ) {
            FollowerScreenState.Content.Loading
        } else {
            FollowerScreenState.Shimmer
        }

        updateState { state -> state.copy(screen = loadingScreen) }

        val page = if (currentState.page == DEFAULT_PAGE) {
            FIRST_PAGE
        } else {
            currentState.page.inc()
        }

        loadingJob = when (val type = currentState.type) {
            is FollowerScreenArgs.Follower -> interactor.getFollowers(
                uuid = type.uuid,
                page = page,
                pageSize = PAGE_SIZE
            )

            is FollowerScreenArgs.Following -> interactor.getFollowing(
                uuid = type.uuid,
                page = page,
                pageSize = PAGE_SIZE
            )
        }.launchFlow(
            each = { data ->
                val screen = if (data.isEmpty()) {
                    FollowerScreenState.Empty
                } else {
                    FollowerScreenState.Content.NotLoading
                }
                updateState { state ->
                    state.copy(
                        page = page,
                        data = currentState.data.plus(data).toImmutableList(),
                        screen = screen
                    )
                }
            },
            onError = { error ->
                if (currentState.data.isEmpty()) {
                    updateState { state ->
                        state.copy(
                            screen = FollowerScreenState.Error(error)
                        )
                    }
                } else {
                    sendEvent(Event.ErrorSnackBar(error.message.orEmpty()))
                }
            }
        )
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val FIRST_PAGE = 1
    }
}
