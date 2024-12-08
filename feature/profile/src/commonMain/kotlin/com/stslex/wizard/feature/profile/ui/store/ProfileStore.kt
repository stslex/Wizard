package com.stslex.wizard.feature.profile.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

interface ProfileStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val uuid: String,
        val isSelf: Boolean,
        val screen: ProfileScreenState
    ) : Store.State {

        companion object {

            val INITIAL = State(
                uuid = "",
                isSelf = false,
                screen = ProfileScreenState.Shimmer
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class Init(
            val type: Screen.Profile.Type,
            val uuid: String
        ) : Action

        data object Logout : Action

        data object RepeatLastAction : Action, Store.Action.RepeatLast

        data object FavouriteClick : Action

        data object FollowingClick : Action

        data object FollowersClick : Action

        data object SettingsClick : Action

        data object BackButtonClick : Action


        sealed interface Navigation : Action, Store.Action.Navigation {

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

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ShowSnackbar(val snackbar: CommonEvents.Snackbar) : Event
    }
}