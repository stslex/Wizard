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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

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

        state.map { it.query }
            .distinctUntilChanged()
            .launchFlow { query ->
                updateState { state ->
                    state.copy(
                        page = DEFAULT_PAGE,
                        query = query,
                    )
                }
                loadNextItems()
            }

        interactor.followItems
            .launchFlow { data ->
                val screen = if (data.isEmpty()) {
                    FollowerScreenState.Empty
                } else {
                    FollowerScreenState.Content.NotLoading
                }
                updateState { state ->
                    state.copy(
                        data = data.toImmutableList(),
                        screen = screen,
                    )
                }
            }
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

        launch(
            action = {
                when (val type = currentState.type) {
                    is FollowerScreenArgs.Follower -> interactor.getFollowers(
                        uuid = type.uuid,
                        query = "", // todo add query
                        page = page,
                        pageSize = PAGE_SIZE
                    )

                    is FollowerScreenArgs.Following -> interactor.getFollowing(
                        uuid = type.uuid,
                        query = "", // todo add query
                        page = page,
                        pageSize = PAGE_SIZE
                    )
                }
            },
            onSuccess = {
                updateState { state ->
                    state.copy(
                        page = page
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
                    updateState { it.copy(screen = FollowerScreenState.Content.NotLoading) }
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
