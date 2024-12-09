package com.stslex.wizard.feature.profile.mvi

import androidx.compose.ui.graphics.Color
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.ui.model.ProfileAvatarModel
import com.stslex.wizard.feature.profile.ui.model.toUi
import com.stslex.wizard.feature.profile.ui.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action.Init
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

class InitStorageHandler(
    private val interactor: ProfileInteractor,
    private val userStore: UserStore,
) : Handler<State, Init, Event, Action>(Init::class) {

    override fun HandlerStore<State, Action, Event>.invoke(action: Init) {
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
}