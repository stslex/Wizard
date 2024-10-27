package com.stslex.wizard.feature.film_feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.core.Logger
import com.stslex.core.ui.mvi.Store
import com.stslex.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.wizard.feature.film_feed.navigation.FeedScreenRouter
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.feature.film_feed.ui.model.toUI
import com.stslex.feature.film_feed.ui.store.FeedStoreComponent.Action
import com.stslex.feature.film_feed.ui.store.FeedStoreComponent.Event
import com.stslex.feature.film_feed.ui.store.FeedStoreComponent.Navigation
import com.stslex.feature.film_feed.ui.store.FeedStoreComponent.State
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job

@Stable
class FeedStore(
    private val interactor: FeedInteractor,
    appDispatcher: AppDispatcher,
    router: FeedScreenRouter
) : Store<State, Event, Action, Navigation>(
    router = router,
    initialState = State.INITIAL,
    appDispatcher = appDispatcher
) {

    private var loadingJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.LoadFilms -> actionLoadFilms()
            is Action.FilmClick -> actionFilmClick(action)
        }
    }

    private fun actionFilmClick(action: Action.FilmClick) {
        consumeNavigation(Navigation.Film(action.filmId))
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

        private const val PAGE_SIZE = 15
    }
}