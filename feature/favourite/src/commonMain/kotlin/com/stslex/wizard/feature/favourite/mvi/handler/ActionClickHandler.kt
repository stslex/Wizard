package com.stslex.wizard.feature.favourite.mvi.handler

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.favourite.di.FavouriteScope
import com.stslex.wizard.feature.favourite.domain.interactor.FavouriteInteractor
import com.stslex.wizard.feature.favourite.mvi.FavouriteHandlerStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action.Click
import com.stslex.wizard.feature.favourite.ui.model.toDomain
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FavouriteScope::class)
@Scoped
internal class ActionClickHandler(
    private val interactor: FavouriteInteractor,
) : Handler<Click, FavouriteHandlerStore> {

    private var likeJob: Job? = null

    override fun FavouriteHandlerStore.invoke(action: Click) {
        when (action) {
            is Click.ItemClick -> actionItemClick(action)
            is Click.LikeClick -> actionLikeClick(action)
        }
    }

    private fun FavouriteHandlerStore.actionItemClick(action: Click.ItemClick) {
        consume(Action.Navigation.OpenFilm(action.uuid))
    }

    private fun FavouriteHandlerStore.actionLikeClick(action: Click.LikeClick) {
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
            onSuccess = {
                logger.i("Favourite item updated: ${newItem.uuid}, isFavourite: ${newItem.isFavourite}")
            },
            onError = { error ->
                consume(Action.ShowError(error))
            },
            eachDispatcher = appDispatcher.default,
            action = {
                interactor.setFavourite(newItem.toDomain())
            })
    }
}