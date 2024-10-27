package com.stslex.wizard.feature.profile.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.profile.ui.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreComponent.Action

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
                onSettingsClick = { onAction(Action.SettingsClick) },
                onBackClick = { onAction(Action.BackButtonClick) }
            )

            ProfileAvatar(avatar = state.data.avatar)

            ProfileInfo(
                username = state.data.username,
                favouriteCount = state.data.favouriteCount,
                followingCount = state.data.following,
                followersCount = state.data.followers,
                onFavouriteClick = { onAction(Action.FavouriteClick) },
                onFollowingClick = { onAction(Action.FollowingClick) },
                onFollowersClick = { onAction(Action.FollowersClick) },
            )
        }

        if (state is ProfileScreenState.Content.Loading) {
            ProfileLoadingIndicator()
        }
    }
}
