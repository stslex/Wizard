package com.stslex.wizard.feature.favourite.ui.store

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.ui.base.mapToAppError
import com.stslex.wizard.core.ui.base.paging.toUi
import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.pager.pager.StorePager
import com.stslex.wizard.core.ui.pager.pager.StorePagerFactory
import com.stslex.wizard.core.ui.pager.states.PagerLoadState
import com.stslex.wizard.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.wizard.feature.favourite.navigation.FavouriteRouter
import com.stslex.wizard.feature.favourite.ui.model.FavouriteModel
import com.stslex.wizard.feature.favourite.ui.model.toDomain
import com.stslex.wizard.feature.favourite.ui.model.toUI
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Event
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.State
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class FavouriteStoreImpl(
    private val interactor: FavouriteInteractor,
    private val router: FavouriteRouter,
    private val appDispatcher: AppDispatcher,
    pagingFactory: StorePagerFactory,
) : BaseStore<State, Action, Event>(
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
        mapper = { it.toUI() },
        config = state.value.paging.config
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
            is Action.Navigation -> router(action)
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
                    paging = pagerState.toUi(currentState.paging.config)
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
                onError = ::showError,
                eachDispatcher = appDispatcher.default
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
        sendAction(Action.Navigation.OpenFilm(action.uuid))
    }

    private fun actionLikeClick(action: Action.LikeClick) {
        if (likeJob?.isActive == true) return
        val items = state.value.paging.items.toMutableList()
        val itemIndex = items
            .indexOfFirst { it.uuid == action.uuid }
            .takeIf { it != -1 }
            ?: return
        val item = state.value.paging.items.getOrNull(itemIndex) ?: return
        val newItem = item.copy(isFavourite = item.isFavourite.not())
        items[itemIndex] = newItem
        updateState { state ->
            state.copy(
                paging = state.paging.copy(
                    items = items.toImmutableList()
                )
            )
        }
        likeJob = launch(
            onSuccess = { /* do nothing */ },
            onError = ::showError,
            eachDispatcher = appDispatcher.default,
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
