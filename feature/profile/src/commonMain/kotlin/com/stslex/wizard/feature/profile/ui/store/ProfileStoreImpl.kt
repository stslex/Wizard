package com.stslex.wizard.feature.profile.ui.store

import androidx.compose.ui.graphics.Color
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.navigation.ProfileRouter
import com.stslex.wizard.feature.profile.ui.model.ProfileAvatarModel
import com.stslex.wizard.feature.profile.ui.model.toUi
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

class ProfileStoreImpl(
    private val interactor: ProfileInteractor,
    private val userStore: UserStore,
    private val router: ProfileRouter,
) : ProfileStore, BaseStore<State, Action, Event>(State.INITIAL) {

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
            is Action.Navigation -> router(action)
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
        sendAction(Action.Navigation.Favourite(state.value.uuid))
    }

    private fun actionFollowingClick() {
        sendAction(Action.Navigation.Following(state.value.uuid))
    }

    private fun actionFollowersClick() {
        sendAction(Action.Navigation.Followers(state.value.uuid))
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
                sendAction(Action.Navigation.LogIn)
            },
            onError = { error ->
                sendEvent(Event.ShowSnackbar(Snackbar.Error(error.message ?: "error logout")))
            }
        )
    }

    private fun actionSettingsClick() {
        sendAction(Action.Navigation.Settings)
    }

    private fun actionBackClick() {
        sendAction(Action.Navigation.Back)
    }
}
