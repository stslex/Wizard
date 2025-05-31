package com.stslex.wizard.feature.match.ui.mvi.handlers

import com.stslex.wizard.core.ui.kit.base.mapToAppError
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.match.di.MatchScope
import com.stslex.wizard.feature.match.domain.interactor.MatchInteractor
import com.stslex.wizard.feature.match.ui.mvi.MatchHandlerStore
import com.stslex.wizard.feature.match.ui.mvi.MatchScreenState
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Action
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Event
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchScope::class)
@Scoped
internal class CommonHandler(
    private val interactor: MatchInteractor
) : Handler<Action.Common, MatchHandlerStore> {

    override fun MatchHandlerStore.invoke(action: Action.Common) {
        when (action) {
            is Action.Common.Logout -> actionLogout()
            is Action.Common.MatchClick -> actionOnMatchClick(action)
            is Action.Common.QueryChanged -> actionOnQueryChanged(action)
            is Action.Common.Retry -> actionRetry()
            is Action.Common.RepeatLastAction -> actionRepeatLastAction()
            is Action.Common.ShowError -> showError(action)
        }
    }

    private fun MatchHandlerStore.actionOnMatchClick(action: Action.Common.MatchClick) {
        consume(Action.Navigation.MatchDetails(action.matchUuid))
    }

    private fun MatchHandlerStore.actionOnQueryChanged(action: Action.Common.QueryChanged) {
        updateState { currentState -> currentState.copy(query = action.query) }
    }

    private fun MatchHandlerStore.actionLogout() {
        launch(
            action = { interactor.logout() },
            onSuccess = { consume(Action.Navigation.LogOut) },
            onError = { consume(Action.Common.ShowError(it)) }
        )
    }

    private fun MatchHandlerStore.actionRetry() {
        consume(Action.Paging.Retry)
    }

    private fun MatchHandlerStore.actionRepeatLastAction() {
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
        consume(lastAction)
    }

    private fun MatchHandlerStore.showError(action: Action.Common.ShowError) {
        val appError = action.error.mapToAppError("error logout")
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
}