package com.stslex.wizard.feature.profile.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.StoreComponent
import com.stslex.wizard.core.ui.mvi.StoreComponent.Event.Snackbar

interface ProfileStoreComponent : StoreComponent {

    @Stable
    data class State(
        val uuid: String,
        val isSelf: Boolean,
        val screen: ProfileScreenState
    ) : StoreComponent.State {

        companion object {

            val INITIAL = State(
                uuid = "",
                isSelf = false,
                screen = ProfileScreenState.Shimmer
            )
        }
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

        @Stable
        data class Init(
            val type: Screen.Profile.Type,
            val uuid: String
        ) : Action

        data object Logout : Action

        data object RepeatLastAction : Action, StoreComponent.Action.RepeatLastAction

        data object FavouriteClick : Action

        data object FollowingClick : Action

        data object FollowersClick : Action

        data object SettingsClick : Action

        data object BackButtonClick : Action
    }

    @Stable
    sealed interface Event : StoreComponent.Event {

        @Stable
        data class ShowSnackbar(val snackbar: Snackbar) : Event
    }

    sealed interface Navigation : StoreComponent.Navigation {

        data object LogIn : Navigation

        data class Favourite(
            val uuid: String
        ) : Navigation

        data class Following(
            val uuid: String
        ) : Navigation

        data class Followers(
            val uuid: String
        ) : Navigation

        data object Back : Navigation

        data object Settings : Navigation
    }
}