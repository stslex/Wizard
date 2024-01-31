package com.stslex.feature.profile.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store
import com.stslex.feature.profile.navigation.ProfileScreenArguments

interface ProfileStoreComponent : Store {

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
            val args: ProfileScreenArguments
        ) : Action

        data object Logout : Action

        data object RepeatLastAction : Action, Store.Action.RepeatLastAction

        data object FavouriteClick : Action

        data object FollowingClick : Action

        data object FollowersClick : Action
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    sealed interface Navigation : Store.Navigation {

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
    }
}