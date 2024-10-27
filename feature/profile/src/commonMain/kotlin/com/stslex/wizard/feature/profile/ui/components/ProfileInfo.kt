package com.stslex.wizard.feature.profile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.theme.AppDimension

@Composable
fun ProfileInfo(
    username: String,
    favouriteCount: Int,
    followingCount: Int,
    followersCount: Int,
    onFavouriteClick: () -> Unit,
    onFollowingClick: () -> Unit,
    onFollowersClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(text = username)

        Spacer(modifier = Modifier.padding(AppDimension.Padding.big))
        ProfileFavouritesCard(
            count = favouriteCount,
            onClick = onFavouriteClick
        )

        Spacer(modifier = Modifier.padding(AppDimension.Padding.small))
        ProfileFollowingCard(
            count = followingCount,
            onClick = onFollowingClick
        )

        Spacer(modifier = Modifier.padding(AppDimension.Padding.small))
        ProfileFollowersCard(
            count = followersCount,
            onClick = onFollowersClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileFavouritesCard(
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(AppDimension.Padding.medium),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(AppDimension.Padding.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "favourite"
            )
            Spacer(modifier = Modifier.padding(AppDimension.Padding.small))
            Text(
                text = count.toString()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileFollowingCard(
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(AppDimension.Padding.medium),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(AppDimension.Padding.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "following"
            )
            Spacer(modifier = Modifier.padding(AppDimension.Padding.small))
            Text(
                text = count.toString()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileFollowersCard(
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(AppDimension.Padding.medium),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(AppDimension.Padding.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "followers"
            )
            Spacer(modifier = Modifier.padding(AppDimension.Padding.small))
            Text(
                text = count.toString()
            )
        }
    }
}