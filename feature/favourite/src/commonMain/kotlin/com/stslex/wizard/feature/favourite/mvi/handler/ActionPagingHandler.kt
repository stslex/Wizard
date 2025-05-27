package com.stslex.wizard.feature.favourite.mvi.handler

import com.stslex.wizard.core.ui.kit.base.paging.toUi
import com.stslex.wizard.core.ui.kit.pager.pager.StorePager
import com.stslex.wizard.core.ui.kit.pager.pager.StorePagerFactory
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.favourite.di.FavouriteScope
import com.stslex.wizard.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.wizard.feature.favourite.mvi.FavouriteHandlerStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.mvi.toUi
import com.stslex.wizard.feature.favourite.ui.model.FavouriteModel
import com.stslex.wizard.feature.favourite.ui.model.toUI
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FavouriteScope::class)
@Scoped
class ActionPagingHandler(
    private val pagingFactory: StorePagerFactory,
    private val interactor: FavouriteInteractor,
) : Handler<Action.Paging, FavouriteHandlerStore> {

    private var _pager: StorePager<FavouriteModel>? = null
    private val pager: StorePager<FavouriteModel>
        get() = requireNotNull(_pager) { "Pager is not initialized" }

    override fun FavouriteHandlerStore.invoke(action: Action.Paging) {
        when (action) {
            Action.Paging.Init -> initPager()
            Action.Paging.LoadMore -> pager.load()
            Action.Paging.Refresh -> pager.refresh(isForceLoad = true)
            Action.Paging.Retry -> pager.retry()
        }
    }

    private fun FavouriteHandlerStore.initPager() {
        if (_pager == null) {
            _pager = pagingFactory.create(
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
            ).also { it.load() }
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
                FavouriteStore.Event.ShowSnackbar(Snackbar.Error("error load matches"))
            )
        }

        state
            .map { it.query }
            .distinctUntilChanged()
            .launch(
                onError = { error -> consume(Action.ShowError(error)) },
                eachDispatcher = appDispatcher.default
            ) {
                if (pager.loadState.value is PagerLoadState.Initial) {
                    pager.initialLoad()
                } else {
                    pager.refresh(isForceLoad = false)
                }
            }
    }
}