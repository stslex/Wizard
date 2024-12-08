package com.stslex.wizard.feature.film_feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.wizard.feature.film_feed.navigation.FeedScreenRouter
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.wizard.feature.film_feed.ui.model.toUI
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.State
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job

@Stable
class FeedStoreImpl(
    private val interactor: FeedInteractor,
    private val router: FeedScreenRouter
) : BaseStore<State, Action, Event>(State.INITIAL), FeedStore {

    private var loadingJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.LoadFilms -> actionLoadFilms()
            is Action.FilmClick -> actionFilmClick(action)
            is Action.Navigation -> router(action)
        }
    }

    private fun actionFilmClick(action: Action.FilmClick) {
        sendAction(Action.Navigation.Film(action.filmId))
    }

    private fun actionLoadFilms() {
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