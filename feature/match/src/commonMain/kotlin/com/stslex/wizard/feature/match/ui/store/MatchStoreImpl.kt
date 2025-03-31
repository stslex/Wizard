package com.stslex.wizard.feature.match.ui.store

import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.kit.base.mapToAppError
import com.stslex.wizard.core.ui.kit.base.paging.toUi
import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.kit.pager.pager.StorePager
import com.stslex.wizard.core.ui.kit.pager.pager.StorePagerFactory
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState
import com.stslex.wizard.feature.match.domain.interactor.MatchInteractor
import com.stslex.wizard.feature.match.navigation.MatchRouter
import com.stslex.wizard.feature.match.ui.model.MatchUiModel
import com.stslex.wizard.feature.match.ui.model.toUi
import com.stslex.wizard.feature.match.ui.store.MatchStore.Action
import com.stslex.wizard.feature.match.ui.store.MatchStore.Event
import com.stslex.wizard.feature.match.ui.store.MatchStore.State
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class MatchStoreImpl(
    pagerFactory: StorePagerFactory,
    private val router: MatchRouter,
    private val interactor: MatchInteractor,
    private val userStore: UserStore,
) : MatchStore, BaseStore<State, Action, Event>(State.INITIAL) {

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
        Logger.d("process: $action", TAG)
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.LoadMore -> actionLoadMore()
            is Action.OnMatchClick -> actionOnMatchClick(action)
            is Action.OnRetryClick -> actionRetryClick()
            is Action.Refresh -> actionRefresh()
            is Action.Logout -> actionLogout()
            is Action.RepeatLastAction -> actionRepeatLastAction()
            is Action.OnQueryChanged -> actionOnQueryChanged(action)
            is Action.Navigation -> router(action)
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
                isSelf = action.type == Screen.Match.Type.SELF,
                uuid = action.uuid.ifBlank { userStore.uuid },
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
        consume(Action.Navigation.MatchDetails(action.matchUuid))
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
                consume(Action.Navigation.LogOut)
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