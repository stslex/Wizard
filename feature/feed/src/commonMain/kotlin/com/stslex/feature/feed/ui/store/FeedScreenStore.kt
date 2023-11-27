package com.stslex.feature.feed.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.core.Logger
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.feed.domain.interactor.FeedInteractor
import com.stslex.feature.feed.navigation.FeedScreenRouter
import com.stslex.feature.feed.ui.model.ScreenState
import com.stslex.feature.feed.ui.model.toUI
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.Action
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.Event
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.Navigation
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.State
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job

class FeedScreenStore(
    private val interactor: FeedInteractor,
    appDispatcher: AppDispatcher,
    router: FeedScreenRouter
) : FeedScreenStoreComponent, BaseStore<State, Event, Action, Navigation>(
    router = router,
    initialState = State.INITIAL,
    appDispatcher = appDispatcher
) {

    private var loadingJob: Job? = null

    override fun sendAction(action: Action) {
        when (action) {
            is Action.LoadFilms -> actionLoadFilms()
            is Action.FilmClick -> actionFilmClick(action)
            is Action.GenreClick -> actionGenreClick(action)
        }
    }

    private fun actionFilmClick(action: Action.FilmClick) {
        navigate(Navigation.Film(action.filmId))
    }

    private fun actionGenreClick(action: Action.GenreClick) {
        navigate(Navigation.SearchGenre(action.genreId))
    }

    private fun actionLoadFilms() {
        if (loadingJob?.isActive == true) {
            Logger.debug("Loading job is active")
            return
        }
        val hasNextPage = state.value.hasNextPage
        if (hasNextPage.not()) {
            Logger.debug("No more pages")
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
                interactor.getFeed(
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