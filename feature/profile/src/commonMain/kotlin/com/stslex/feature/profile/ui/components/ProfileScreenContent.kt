package com.stslex.feature.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.core.ui.base.image.NetworkImage
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.profile.ui.model.ProfileAvatarModel
import com.stslex.feature.profile.ui.store.ProfileScreenState
import com.stslex.feature.profile.ui.store.ProfileStoreComponent

@Composable
internal fun ProfileScreenContent(
    state: ProfileScreenState.Content,
    onAction: (ProfileStoreComponent.Action) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
        ) {
            when (val avatar = state.data.avatar) {
                is ProfileAvatarModel.Empty -> {
                    Text(
                        text = avatar.symbol,
                        color = avatar.color
                    )
                }

                is ProfileAvatarModel.Content -> {
                    NetworkImage(
                        url = avatar.url,
                        modifier = Modifier
                            .padding(AppDimension.Padding.big)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }


            TextButton(
                onClick = {
                    onAction(ProfileStoreComponent.Action.Logout)
                }
            ) {
                Text(text = "Logout")
            }
            Text(
                text = state.data.username
            )

            Spacer(modifier = Modifier.padding(AppDimension.Padding.big))

            ProfileFavouritesCard(
                count = state.data.favouriteCount,
                onClick = {
                    onAction(ProfileStoreComponent.Action.FavouriteClick)
                }
            )

            Spacer(modifier = Modifier.padding(AppDimension.Padding.small))

            ProfileFollowingCard(
                count = state.data.following,
                onClick = {
                    onAction(ProfileStoreComponent.Action.FollowingClick)
                }
            )

            Spacer(modifier = Modifier.padding(AppDimension.Padding.small))
            ProfileFollowersCard(
                count = state.data.followers,
                onClick = {
                    onAction(ProfileStoreComponent.Action.FollowersClick)
                }
            )
        }

        if (state is ProfileScreenState.Content.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}