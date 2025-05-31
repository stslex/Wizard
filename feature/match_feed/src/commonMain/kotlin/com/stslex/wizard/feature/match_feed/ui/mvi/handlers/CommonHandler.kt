package com.stslex.wizard.feature.match_feed.ui.mvi.handlers

import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.match_feed.di.MatchDetailsScope
import com.stslex.wizard.feature.match_feed.domain.MatchFeedInteractor
import com.stslex.wizard.feature.match_feed.ui.model.toUI
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedHandlerStore
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Event
import com.stslex.wizard.feature.match_feed.ui.mvi.ScreenState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchDetailsScope::class)
@Scoped
internal class CommonHandler(
    private val interactor: MatchFeedInteractor
) : Handler<Action.Common, MatchFeedHandlerStore> {

    private var loadingJob: Job? = null

    override fun MatchFeedHandlerStore.invoke(action: Action.Common) {
        when (action) {
            Action.Common.Init -> actionInit()
            Action.Common.LoadFilms -> actionLoadFilms()
        }
    }

    private fun MatchFeedHandlerStore.actionInit() {
        interactor
            .getLatestMatch()
            .launch { match ->
                updateState { currentState ->
                    currentState.copy(
                        screen = ScreenState.Content.Success,
                        match = match.toUI()
                    )
                }
                consume(Action.Common.LoadFilms)
            }
    }

    private fun MatchFeedHandlerStore.actionLoadFilms() {
        val uuid = state.value.match?.uuid
        if (uuid == null) {
            Logger.d("Match uuid is null")
            return
        }

        if (loadingJob?.isActive == true) {
            Logger.d("Loading job is active")
            return
        }
        val hasNextPage = state.value.hasNextPage
        if (hasNextPage.not()) {
            Logger.d("No more pages")
            return
        }
        val loadScreenState = when (state.value.screen) {
            is ScreenState.Content -> ScreenState.Content.AppendLoading
            is ScreenState.Loading -> ScreenState.Loading
            is ScreenState.Error -> ScreenState.Loading
        }

        updateState { currentState ->
            currentState.copy(
                screen = loadScreenState
            )
        }

        val currentPage = state.value.currentPage
        loadingJob = launch(
            action = {
                interactor.getMatchFilms(
                    matchUuid = uuid,
                    page = currentPage.inc(),
                    pageSize = PAGE_SIZE
                )
            },
            onSuccess = { feed ->
                val films = feed.films.toUI()
                updateState { currentState ->
                    val currentFilms = currentState.films.toMutableList()
                    currentFilms.addAll(films)
                    currentState.copy(
                        films = currentFilms.toImmutableList(),
                        screen = ScreenState.Content.Success,
                        currentPage = currentPage.inc(),
                        hasNextPage = films.size == PAGE_SIZE
                    )
                }
            },
            onError = { throwable ->
                if (state.value.films.isEmpty()) {
                    updateState {
                        it.copy(
                            screen = ScreenState.Error(throwable.message ?: "Unknown error")
                        )
                    }
                } else {
                    sendEvent(Event.ErrorSnackBar(throwable.message ?: "Unknown error"))
                }
            },
        )
    }

    companion object {

        private const val PAGE_SIZE = 5
    }
}