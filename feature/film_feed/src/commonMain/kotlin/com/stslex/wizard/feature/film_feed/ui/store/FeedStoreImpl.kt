package com.stslex.wizard.feature.film_feed.ui.store

import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.wizard.feature.film_feed.ui.model.toUI
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.State
import kotlinx.coroutines.Job
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FeedStoreImpl(
    private val interactor: FeedInteractor,
    private val component: FilmFeedComponent
) : BaseStore<State, Action, Event>(State.INITIAL), FeedStore {

    private var loadingJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.LoadFilms -> actionLoadFilms()
            is Action.FilmClick -> actionFilmClick(action)
            is Action.Navigation -> actionNavigation(action)
        }
    }

    private fun actionNavigation(action: Action.Navigation) {
        when (action) {
            is Action.Navigation.Film -> component.openFilm(action.filmId)
        }
    }

    private fun actionFilmClick(action: Action.FilmClick) {
        consume(Action.Navigation.Film(action.filmId))
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
            is ScreenState.Content.Shimmer -> ScreenState.Content.Shimmer
            is ScreenState.Content.Success, is ScreenState.Content.AppendLoading -> ScreenState.Content.AppendLoading
            is ScreenState.Error -> ScreenState.Content.Shimmer
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
                    currentState.films.addItems(films)
                    currentState.copy(
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