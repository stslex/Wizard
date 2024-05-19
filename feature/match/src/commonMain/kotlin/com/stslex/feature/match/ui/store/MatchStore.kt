package com.stslex.feature.match.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.core.Logger
import com.stslex.core.database.store.UserStore
import com.stslex.core.ui.base.mapToAppError
import com.stslex.core.ui.base.paging.toUi
import com.stslex.core.ui.mvi.Store
import com.stslex.core.ui.mvi.StoreComponent.Event.Snackbar
import com.stslex.core.ui.pager.pager.StorePager
import com.stslex.core.ui.pager.pager.StorePagerFactory
import com.stslex.core.ui.pager.states.PagerLoadState
import com.stslex.feature.match.domain.interactor.MatchInteractor
import com.stslex.feature.match.navigation.MatchRouter
import com.stslex.feature.match.ui.model.MatchUiModel
import com.stslex.feature.match.ui.model.toUi
import com.stslex.feature.match.ui.store.MatchStoreComponent.Action
import com.stslex.feature.match.ui.store.MatchStoreComponent.Event
import com.stslex.feature.match.ui.store.MatchStoreComponent.Navigation
import com.stslex.feature.match.ui.store.MatchStoreComponent.State
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class MatchStore(
    appDispatcher: AppDispatcher,
    router: MatchRouter,
    pagerFactory: StorePagerFactory,
    private val interactor: MatchInteractor,
    private val userStore: UserStore,
) : Store<State, Event, Action, Navigation>(
    appDispatcher = appDispatcher,
    router = router,
    initialState = State.INITIAL
) {

    private val pager: StorePager<MatchUiModel> = pagerFactory.create(
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

    override fun process(action: Action) {
        Logger.debug("process: $action", TAG)
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.LoadMore -> actionLoadMore()
            is Action.OnMatchClick -> actionOnMatchClick(action)
            is Action.OnRetryClick -> actionRetryClick()
            is Action.Refresh -> actionRefresh()
            is Action.Logout -> actionLogout()
            is Action.RepeatLastAction -> actionRepeatLastAction()
            is Action.OnQueryChanged -> actionOnQueryChanged(action)
        }
    }

    private fun actionInit(action: Action.Init) {
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

        updateState { currentState ->
            currentState.copy(
                isSelf = action.args.isSelf,
                uuid = action.args.uuid ?: userStore.uuid,
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
        pager.refresh(isForceLoad = true)
    }

    private fun actionLogout() {
        launch(
            action = {
                interactor.logout()
            },
            onSuccess = {
                navigate(Navigation.LogOut)
            },
            onError = ::showError
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

    private fun actionOnQueryChanged(action: Action.OnQueryChanged) {
        updateState { currentState ->
            currentState.copy(
                query = action.query
            )
        }
    }

    private fun showError(error: Throwable) {
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

    companion object {
        private const val TAG = "MatchStore"
    }
}