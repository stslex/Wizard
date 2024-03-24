package com.stslex.feature.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.feature.profile.ui.store.ProfileScreenState
import com.stslex.feature.profile.ui.store.ProfileStoreComponent

@Composable
internal fun ProfileScreenContent(
    state: ProfileScreenState.Content,
    onAction: (ProfileStoreComponent.Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
        ) {
            ProfileAvatar(avatar = state.data.avatar)

            if (state.data.isCurrentUser) {
                ProfileLogoutButton(
                    onClick = { onAction(ProfileStoreComponent.Action.Logout) },
                )
            }

            ProfileInfo(
                username = state.data.username,
                favouriteCount = state.data.favouriteCount,
                followingCount = state.data.following,
                followersCount = state.data.followers,
                onFavouriteClick = { onAction(ProfileStoreComponent.Action.FavouriteClick) },
                onFollowingClick = { onAction(ProfileStoreComponent.Action.FollowingClick) },
                onFollowersClick = { onAction(ProfileStoreComponent.Action.FollowersClick) },
            )
        }

        if (state is ProfileScreenState.Content.Loading) {
            ProfileLoadingIndicator()
        }
    }
}
