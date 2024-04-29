package com.stslex.feature.favourite.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.base.mapToAppError
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.core.ui.pager.pager.StorePager
import com.stslex.core.ui.pager.pager.StorePagerFactory
import com.stslex.core.ui.pager.states.PagerLoadState
import com.stslex.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.feature.favourite.navigation.FavouriteRouter
import com.stslex.feature.favourite.ui.model.FavouriteModel
import com.stslex.feature.favourite.ui.model.toDomain
import com.stslex.feature.favourite.ui.model.toUI
import com.stslex.feature.favourite.ui.store.FavouriteStore.Action
import com.stslex.feature.favourite.ui.store.FavouriteStore.Event
import com.stslex.feature.favourite.ui.store.FavouriteStore.Navigation
import com.stslex.feature.favourite.ui.store.FavouriteStore.State
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class FavouriteStoreImpl(
    private val interactor: FavouriteInteractor,
    router: FavouriteRouter,
    appDispatcher: AppDispatcher,
    pagingFactory: StorePagerFactory,
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL
), FavouriteStore {

    private var likeJob: Job? = null

    private val pager: StorePager<FavouriteModel> = pagingFactory.create(
        request = { page, pageSize ->
            interactor.getFavourites(
                uuid = state.value.uuid,
                query = state.value.query,
                page = page,
                pageSize = pageSize
            )
        },
        scope = scope,
        mapper = { it.toUI() }
    )

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.LoadMore -> actionLoadMore()
            is Action.ItemClick -> actionItemClick(action)
            is Action.LikeClick -> actionLikeClick(action)
            is Action.InputSearch -> actionInputSearch(action)
            is Action.Refresh -> actionRefresh()
            is Action.Retry -> actionRetryClick()
        }
    }

    private fun actionInit(action: Action.Init) {
        updateState { state ->
            state.copy(
                uuid = action.uuid,
            )
        }

        pager.state.launch { pagerState ->
            updateState { currentState ->
                currentState.copy(
                    pagingState = pagerState
                )
            }
        }
        pager.loadState.launch { loadState ->
            updateState { currentState ->
                currentState.copy(
                    screen = loadState.toUi()
                )
            }
        }
        pager.loadEvents.launch {
            sendEvent(
                Event.ShowSnackbar(Snackbar.Error("error load matches"))
            )
        }

        updateState { currentState ->
            currentState.copy(uuid = action.uuid)
        }

        state
            .map { it.query }
            .distinctUntilChanged()
            .launch(
                onError = ::showError
            ) {
                if (pager.loadState.value is PagerLoadState.Initial) {
                    pager.initialLoad()
                } else {
                    pager.refresh(isForceLoad = false)
                }
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
        val items = state.value.pagingState.result.toMutableList()
        val itemIndex = items
            .indexOfFirst {
                it.uuid == action.uuid
            }
            .takeIf { it != -1 }
            ?: return
        val item = state.value.pagingState.result.getOrNull(itemIndex) ?: return
        val newItem = item.copy(isFavourite = item.isFavourite.not())
        items[itemIndex] = newItem
        updateState { state ->
            state.copy(
                pagingState = state.pagingState.copy(
                    result = items.toImmutableList()
                )
            )
        }
        likeJob = launch(
            onSuccess = { /* do nothing */ },
            onError = ::showError,
            action = {
                interactor.setFavourite(newItem.toDomain())
            })
    }

    private fun actionLoadMore() {
        pager.load()
    }

    private fun actionRefresh() {
        pager.refresh(isForceLoad = true)
    }

    private fun actionRetryClick() {
        pager.retry()
    }

    private fun showError(error: Throwable) {
        val appError = error.mapToAppError("error logout")
        if (state.value.screen is FavouriteScreenState.Content) {
            sendEvent(
                Event.ShowSnackbar(Snackbar.Error(appError.message))
            )
        } else {
            updateState { currentState ->
                currentState.copy(
                    screen = FavouriteScreenState.Error(appError)
                )
            }
        }
    }
}
