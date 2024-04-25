package com.stslex.feature.match.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.core.paging.PagingCoreData.Companion.DEFAULT_PAGE
import com.stslex.core.database.store.UserStore
import com.stslex.core.ui.base.mapToAppError
import com.stslex.core.ui.base.paging.pagingMap
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.feature.match.domain.interactor.MatchInteractor
import com.stslex.feature.match.navigation.MatchRouter
import com.stslex.feature.match.ui.model.toUi
import com.stslex.feature.match.ui.store.MatchStoreComponent.Action
import com.stslex.feature.match.ui.store.MatchStoreComponent.Event
import com.stslex.feature.match.ui.store.MatchStoreComponent.Navigation
import com.stslex.feature.match.ui.store.MatchStoreComponent.State
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job

class MatchStore(
    appDispatcher: AppDispatcher,
    router: MatchRouter,
    private val interactor: MatchInteractor,
    private val userStore: UserStore
) : BaseStore<State, Event, Action, Navigation>(
    appDispatcher = appDispatcher,
    router = router,
    initialState = State.INITIAL
) {

    private var loadJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.LoadMore -> actionLoadMore()
            is Action.OnMatchClick -> actionOnMatchClick(action)
            is Action.OnRetryClick -> actionRetryClick()
            is Action.Refresh -> actionRefresh()
            is Action.Logout -> actionLogout()
            is Action.RepeatLastAction -> actionRepeatLastAction()
        }
    }

    private fun actionInit(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(
                isSelf = action.args.isSelf,
                uuid = action.args.uuid ?: userStore.uuid,
                pagingState = currentState.pagingState.copy(
                    hasMore = true
                )
            )
        }
        if (state.value.pagingState.result.isEmpty()) {
            loadItems(isForceLoad = true)
        }
    }

    private fun actionLoadMore() {
        if (
            state.value.pagingState.hasMore.not() ||
            state.value.screen !is MatchScreenState.Content.Data
        ) {
            return
        }
        updateState { currentState ->
            currentState.copy(
                screen = MatchScreenState.Content.Append
            )
        }
        loadItems(isForceLoad = false)
    }

    private fun actionOnMatchClick(action: Action.OnMatchClick) {
        navigate(Navigation.MatchDetails(action.matchUuid))
    }

    private fun actionRetryClick() {
        if (
            state.value.screen !is MatchScreenState.Error ||
            state.value.screen is MatchScreenState.Shimmer
        ) {
            return
        }
        updateState { currentState ->
            currentState.copy(
                screen = MatchScreenState.Shimmer
            )
        }
        loadItems(isForceLoad = false)
    }

    private fun actionRefresh() {
        updateState { currentState ->
            currentState.copy(
                screen = MatchScreenState.Content.Refresh,
                pagingState = currentState.pagingState.copy(
                    page = DEFAULT_PAGE
                )
            )
        }
        loadItems(isForceLoad = true)
    }

    private fun loadItems(isForceLoad: Boolean) {
        if (loadJob?.isActive == true && isForceLoad.not()) {
            return
        }
        loadJob?.cancel()
        loadJob = launch(
            action = {
                interactor.getMatches(
                    uuid = state.value.uuid,
                    page = state.value.pagingState.page,
                    pageSize = state.value.pagingState.pageSize,
                )
            },
            onSuccess = { result ->
                val newPagingState = result.pagingMap { it.toUi() }
                if (
                    newPagingState.result.isEmpty() &&
                    (state.value.pagingState.page == DEFAULT_PAGE || state.value.pagingState.result.isEmpty())
                ) {
                    updateState { currentState ->
                        currentState.copy(
                            screen = MatchScreenState.Empty,
                            pagingState = newPagingState
                        )
                    }
                    return@launch
                }
                val newItems = if (state.value.pagingState.page == DEFAULT_PAGE) {
                    newPagingState.result
                } else {
                    (state.value.pagingState.result + newPagingState.result).toImmutableList()
                }
                updateState { currentState ->
                    currentState.copy(
                        screen = MatchScreenState.Content.Data,
                        pagingState = newPagingState.copy(
                            result = newItems
                        )
                    )
                }
            },
            onError = { error ->
                val appError = error.mapToAppError("error load matches")
                if (state.value.screen is MatchScreenState.Content) {
                    sendEvent(
                        Event.ShowSnackbar(Snackbar.Error(appError.message))
                    )
                } else {
                    updateState { currentState ->
                        currentState.copy(
                            screen = MatchScreenState.Error(appError)
                        )
                    }
                }
            }
        )
    }

    private fun actionLogout() {
        launch(
            action = {
                interactor.logout()
            },
            onSuccess = {
                navigate(Navigation.LogOut)
            },
            onError = { error ->
                val appError = error.mapToAppError("error logout")
                if (state.value.screen is MatchScreenState.Content) {
                    sendEvent(
                        Event.ShowSnackbar(Snackbar.Error(appError.message))
                    )
                } else {
                    updateState { currentState ->
                        currentState.copy(
                            screen = MatchScreenState.Error(appError)
                        )
                    }
                }
            }
        )
    }

    private fun actionRepeatLastAction() {
        val lastAction = lastAction ?: return
        updateState { currentState ->
            val screen = when (currentState.screen) {
                is MatchScreenState.Content -> MatchScreenState.Content.Refresh
                is MatchScreenState.Error,
                is MatchScreenState.Shimmer,
                is MatchScreenState.Empty -> MatchScreenState.Shimmer
            }
            currentState.copy(screen = screen)
        }
        process(lastAction)
    }
}