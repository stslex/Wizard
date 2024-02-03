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

class FavouriteStore(
    private val interactor: FavouriteInteractor,
    router: FavouriteRouter,
    appDispatcher: AppDispatcher
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL
) {

    private var loadingJob: Job? = null
    private var likeJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.LoadMore -> actionLoadMore()
            is Action.ItemClick -> actionItemClick(action)
            is Action.LikeClick -> actionLikeClick(action)
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
        loadNextItems()
    }

    private fun actionLoadMore() {
        loadNextItems()
    }

    private fun loadNextItems() {
        if (loadingJob?.isActive == true) return

        val currentState = state.value

        val loadingScreen = if (
            currentState.screen is FavouriteScreenState.Content &&
            currentState.data.isNotEmpty()
        ) {
            FavouriteScreenState.Content.Loading
        } else {
            FavouriteScreenState.Shimmer
        }

        updateState { state -> state.copy(screen = loadingScreen) }

        val page = if (currentState.page == DEFAULT_PAGE) {
            FIRST_PAGE
        } else {
            currentState.page.inc()
        }

        loadingJob = interactor.getFavourites(
            uuid = state.value.uuid,
            page = page,
            pageSize = PAGE_SIZE
        ).launchFlow(
            each = { data ->
                val uiData = data.map { favourite -> favourite.toUI() }
                val screenItems = currentState.data.plus(uiData).toImmutableList()
                val screen = if (screenItems.isEmpty()) {
                    FavouriteScreenState.Empty
                } else {
                    FavouriteScreenState.Content.NotLoading
                }
                updateState { state ->
                    state.copy(
                        page = page,
                        data = screenItems,
                        screen = screen
                    )
                }
            },
            onError = { error ->
                if (currentState.data.isEmpty()) {
                    updateState { state ->
                        state.copy(
                            screen = FavouriteScreenState.Error(error)
                        )
                    }
                } else {
                    sendEvent(Event.ErrorSnackBar(error.message.orEmpty()))
                }
            }
        )
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val FIRST_PAGE = 1
    }
}
