package com.stslex.wizard.feature.film_feed.mvi

import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.film_feed.di.FilmFeedScope
import com.stslex.wizard.feature.film_feed.domain.interactor.FeedInteractor
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.wizard.feature.film_feed.ui.model.toUI
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.Action.LoadFilms
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.Event
import kotlinx.coroutines.Job
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FilmFeedScope::class)
@Scoped
internal class LoadFilmsHandler(
    private val interactor: FeedInteractor,
) : Handler<LoadFilms, FeedHandlerStore> {

    private var loadingJob: Job? = null


    override fun FeedHandlerStore.invoke(action: LoadFilms) {
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

    internal companion object {

        private const val PAGE_SIZE = 15
    }
}