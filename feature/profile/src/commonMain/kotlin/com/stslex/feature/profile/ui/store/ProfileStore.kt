package com.stslex.feature.profile.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.database.store.UserStore
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.feature.profile.navigation.ProfileRouter
import com.stslex.feature.profile.ui.model.toUi
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Action
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Event
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Navigation
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.State

class ProfileStore(
    private val interactor: ProfileInteractor,
    private val userStore: UserStore,
    router: ProfileRouter,
    appDispatcher: AppDispatcher,
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.Logout -> actionLogout()
            is Action.RepeatLastAction -> actionRepeatLastAction()
            is Action.FavouriteClick -> actionFavouriteClick()
            is Action.FollowingClick -> actionFollowingClick()
            is Action.FollowersClick -> actionFollowersClick()
        }
    }

    private fun actionInit(action: Action.Init) {
        val uuid = action.args.uuid ?: userStore.uuid

        updateState { currentState ->
            currentState.copy(
                isSelf = action.args.isSelf,
                uuid = uuid,
            )
        }

        interactor.getProfile(uuid)
            .launchFlow(
                onError = { error ->
                    updateState { currentState ->
                        currentState.copy(
                            screen = ProfileScreenState.Error(error)
                        )
                    }
                }
            ) { profile ->
                updateState { currentState ->
                    currentState.copy(
                        screen = ProfileScreenState.Content.NotLoading(profile.toUi())
                    )
                }
            }
    }

    private fun actionFavouriteClick() {
        navigate(Navigation.Favourite(state.value.uuid))
    }

    private fun actionFollowingClick() {
        navigate(Navigation.Following(state.value.uuid))
    }

    private fun actionFollowersClick() {
        navigate(Navigation.Followers(state.value.uuid))
    }

    private fun actionRepeatLastAction() {
        val lastAction = lastAction ?: return
        process(lastAction)
    }

    private fun actionLogout() {
        val currentScreen = state.value.screen

        if (
            currentScreen is ProfileScreenState.Content.Loading ||
            currentScreen is ProfileScreenState.Shimmer
        ) {
            return
        }

        updateState { currentState ->
            currentState.copy(
                screen = ProfileScreenState.Shimmer
            )
        }

        launch(
            action = {
                interactor.logOut()
            },
            onSuccess = {
                navigate(Navigation.LogIn)
            },
            onError = { error ->
                sendEvent(Event.ShowSnackbar(Snackbar.Error(error.message ?: "error logout")))
            }
        )
    }
}
