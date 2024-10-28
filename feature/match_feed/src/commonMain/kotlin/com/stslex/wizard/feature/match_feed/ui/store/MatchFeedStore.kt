package com.stslex.wizard.feature.match_feed.ui.store

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.match_feed.domain.MatchFeedInteractor
import com.stslex.wizard.feature.match_feed.navigation.MatchFeedRouter
import com.stslex.wizard.feature.match_feed.ui.model.toUI
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreComponent.Action
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreComponent.Event
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreComponent.Navigation
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreComponent.State
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job

class MatchFeedStore(
    private val interactor: MatchFeedInteractor,
    appDispatcher: AppDispatcher,
    router: MatchFeedRouter
) : Store<State, Event, Action, Navigation>(
    initialState = State.INITIAL,
    appDispatcher = appDispatcher,
    router = router
) {

    private var loadingJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            Action.Init -> actionInit()
            Action.LoadFilms -> actionLoadFilms()
            is Action.FilmClick -> actionFilmClick(action)
            is Action.FilmSwiped -> actionFilmSwiped(action)
        }
    }

    private fun actionFilmSwiped(action: Action.FilmSwiped) {
        // todo send action to backend
    }

    private fun actionFilmClick(action: Action.FilmClick) {
        consumeNavigation(Navigation.Film(action.uuid))
    }

    private fun actionInit() {
        interactor
            .getLatestMatch()
            .launch { match ->
                updateState { currentState ->
                    currentState.copy(
                        screen = ScreenState.Content.Success,
                        match = match.toUI()
                    )
                }
                loadFilms(match.uuid)
            }
    }

    private fun actionLoadFilms() {
        val matchUuid = state.value.match?.uuid
        if (matchUuid == null) {
            Logger.d("Match uuid is null")
            return
        }
        loadFilms(matchUuid)
    }

    private fun loadFilms(uuid: String) {
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