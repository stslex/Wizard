package com.stslex.wizard.feature.profile.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileStore.Action

@Composable
internal fun ProfileScreenContent(
    state: ProfileScreenState.Content,
    onAction: (Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileScreenToolbar(
                nickname = state.data.username,
                isCurrentUser = state.data.isCurrentUser,
                onSettingsClick = { onAction(Action.Click.SettingsClick) },
                onBackClick = { onAction(Action.Click.BackButtonClick) }
            )

            ProfileAvatar(avatar = state.data.avatar)

            ProfileInfo(
                username = state.data.username,
                favouriteCount = state.data.favouriteCount,
                followingCount = state.data.following,
                followersCount = state.data.followers,
                onFavouriteClick = { onAction(Action.Click.FavouriteClick) },
                onFollowingClick = { onAction(Action.Click.FollowingClick) },
                onFollowersClick = { onAction(Action.Click.FollowersClick) },
            )
        }

        if (state is ProfileScreenState.Content.Loading) {
            ProfileLoadingIndicator()
        }
    }
}
