package com.stslex.wizard.feature.profile.ui.store

import androidx.compose.ui.graphics.Color
import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.StoreComponent.Event.Snackbar
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.navigation.ProfileRouter
import com.stslex.wizard.feature.profile.ui.model.ProfileAvatarModel
import com.stslex.wizard.feature.profile.ui.model.toUi
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreComponent.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreComponent.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreComponent.Navigation
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreComponent.State

class ProfileStore(
    private val interactor: ProfileInteractor,
    private val userStore: UserStore,
    router: ProfileRouter,
    appDispatcher: AppDispatcher,
) : Store<State, Event, Action, Navigation>(
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
            is Action.SettingsClick -> actionSettingsClick()
            is Action.BackButtonClick -> actionBackClick()
        }
    }

    private fun actionInit(action: Action.Init) {
        val uuid = action.uuid.ifBlank { userStore.uuid }

        updateState { currentState ->
            currentState.copy(
                isSelf = action.type == Screen.Profile.Type.SELF,
                uuid = uuid,
            )
        }

        interactor.getProfile(uuid)
            .launch(
                onError = { error ->
                    updateState { currentState ->
                        currentState.copy(
                            screen = ProfileScreenState.Error(error)
                        )
                    }
                }
            ) { profile ->
                val avatar = if (profile.avatarUrl.isBlank()) {
                    ProfileAvatarModel.Empty(
                        color = Color.Gray, // TODO replace with random color
                        symbol = profile.username.firstOrNull()?.lowercase().orEmpty()
                    )
                } else {
                    ProfileAvatarModel.Content(profile.avatarUrl)
                }
                val profileUi = profile.toUi(
                    avatarModel = avatar
                )
                updateState { currentState ->
                    currentState.copy(
                        screen = ProfileScreenState.Content.NotLoading(profileUi)
                    )
                }
            }
    }

    private fun actionFavouriteClick() {
        consumeNavigation(Navigation.Favourite(state.value.uuid))
    }

    private fun actionFollowingClick() {
        consumeNavigation(Navigation.Following(state.value.uuid))
    }

    private fun actionFollowersClick() {
        consumeNavigation(Navigation.Followers(state.value.uuid))
    }

    private fun actionRepeatLastAction() {
        val lastAction = lastAction ?: return
        updateState { currentState ->
            val screen = when (val screen = currentState.screen) {
                is ProfileScreenState.Content -> ProfileScreenState.Content.Loading(screen.data)
                is ProfileScreenState.Error, is ProfileScreenState.Shimmer -> ProfileScreenState.Shimmer
            }
            currentState.copy(screen = screen)
        }
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
                consumeNavigation(Navigation.LogIn)
            },
            onError = { error ->
                sendEvent(Event.ShowSnackbar(Snackbar.Error(error.message ?: "error logout")))
            }
        )
    }

    private fun actionSettingsClick() {
        consumeNavigation(Navigation.Settings)
    }

    private fun actionBackClick() {
        consumeNavigation(Navigation.Back)
    }
}
