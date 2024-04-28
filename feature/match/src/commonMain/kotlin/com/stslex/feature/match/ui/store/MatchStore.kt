package com.stslex.feature.match.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.database.store.UserStore
import com.stslex.core.ui.base.mapToAppError
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.core.ui.pager.StorePager
import com.stslex.core.ui.pager.StorePagerImpl
import com.stslex.feature.match.domain.interactor.MatchInteractor
import com.stslex.feature.match.navigation.MatchRouter
import com.stslex.feature.match.ui.model.MatchUiModel
import com.stslex.feature.match.ui.model.toUi
import com.stslex.feature.match.ui.store.MatchStoreComponent.Action
import com.stslex.feature.match.ui.store.MatchStoreComponent.Event
import com.stslex.feature.match.ui.store.MatchStoreComponent.Navigation
import com.stslex.feature.match.ui.store.MatchStoreComponent.State

// todo refactor pager state for UI https://github.com/stslex/Wizard/issues/35
// todo add base store pager binding https://github.com/stslex/Wizard/issues/36
// todo add query support for pager https://github.com/stslex/Wizard/issues/37

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

    private val pager: StorePager<MatchUiModel> = StorePagerImpl(
        request = { page, pageSize ->
            interactor.getMatches(
                uuid = state.value.uuid,
                page = page,
                pageSize = pageSize
            )
        },
        scope = scope,
        mapper = { it.toUi() }
    )

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
        pager.state.launch { pagerState ->
            updateState { currentState ->
                currentState.copy(
                    pagingState = pagerState
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
        pager.initialLoad()
        updateState { currentState ->
            currentState.copy(
                isSelf = action.args.isSelf,
                uuid = action.args.uuid ?: userStore.uuid,
            )
        }
        pager.initialLoad()
    }

    private fun actionLoadMore() {
        pager.load()
    }

    private fun actionOnMatchClick(action: Action.OnMatchClick) {
        navigate(Navigation.MatchDetails(action.matchUuid))
    }

    private fun actionRetryClick() {
        pager.retry()
    }

    private fun actionRefresh() {
        pager.refresh()
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