package com.stslex.feature.favourite.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.feature.favourite.navigation.FavouriteRouter
import com.stslex.feature.favourite.ui.model.toDomain
import com.stslex.feature.favourite.ui.model.toUI
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.Action
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.Event
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.Navigation
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.State
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.State.Companion.DEFAULT_PAGE
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class FavouriteStore(
    private val interactor: FavouriteInteractor,
    router: FavouriteRouter,
    appDispatcher: AppDispatcher
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL
) {
    private var likeJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.LoadMore -> actionLoadMore()
            is Action.ItemClick -> actionItemClick(action)
            is Action.LikeClick -> actionLikeClick(action)
            is Action.InputSearch -> actionInputSearch(action)
        }
    }

    private fun actionInputSearch(action: Action.InputSearch) {
        updateState { state ->
            state.copy(query = action.query)
        }
    }

    private fun actionItemClick(action: Action.ItemClick) {
        navigate(Navigation.OpenFilm(action.uuid))
    }

    private fun actionLikeClick(action: Action.LikeClick) {
        if (likeJob?.isActive == true) return
        val item = state.value.data.firstOrNull { it.uuid == action.uuid } ?: return
        val isFavourite = item.isFavourite.not()
        updateState { state ->
            state.copy(
                data = state.data.map { favourite ->
                    if (favourite.uuid == item.uuid) {
                        favourite.copy(isFavourite = isFavourite)
                    } else {
                        favourite
                    }
                }.toImmutableList()
            )
        }
        likeJob = launch(
            onSuccess = { /* do nothing */ },
            onError = { error ->
                sendEvent(Event.ErrorSnackBar(error.message.orEmpty()))
            },
            action = {
                interactor.setFavourite(
                    model = item.toDomain().copy(isFavourite = isFavourite)
                )
            })
    }

    private fun actionInit(action: Action.Init) {
        updateState { state ->
            state.copy(
                uuid = action.uuid,
            )
        }

        state.map { it.query }
            .distinctUntilChanged()
            .launchFlow { query ->
                updateState { state ->
                    state.copy(
                        page = DEFAULT_PAGE,
                        query = query,
                    )
                }
                loadNextItems()
            }

        interactor.favourites
            .launchFlow { data ->
                val screen = if (data.isEmpty()) {
                    FavouriteScreenState.Content.Empty
                } else {
                    FavouriteScreenState.Content.Data
                }
                updateState { state ->
                    state.copy(
                        data = data.map { it.toUI() }.toImmutableList(),
                        screen = screen,
                        isLoading = false
                    )
                }
            }
    }

    private fun actionLoadMore() {
        loadNextItems()
    }

    private fun loadNextItems() {
        val currentState = state.value
        val loadingScreen = if (
            currentState.screen is FavouriteScreenState.Content &&
            currentState.data.isNotEmpty()
        ) {
            currentState.screen
        } else {
            FavouriteScreenState.Shimmer
        }

        updateState { state ->
            state.copy(
                screen = loadingScreen,
                isLoading = true
            )
        }

        val page = if (currentState.page == DEFAULT_PAGE) {
            FIRST_PAGE
        } else {
            currentState.page.inc()
        }

        launch(
            onSuccess = {
                updateState { state ->
                    state.copy(
                        page = page
                    )
                }
            },
            onError = { error ->
                if (currentState.data.isEmpty()) {
                    updateState { state ->
                        state.copy(
                            screen = FavouriteScreenState.Error(error),
                            isLoading = false
                        )
                    }
                } else {
                    updateState { it.copy(isLoading = false) }
                    sendEvent(Event.ErrorSnackBar(error.message.orEmpty()))
                }
            }
        ) {
            interactor.getFavourites(
                uuid = state.value.uuid,
                query = state.value.query,
                page = page,
                pageSize = PAGE_SIZE,
            )
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val FIRST_PAGE = 1
    }
}
