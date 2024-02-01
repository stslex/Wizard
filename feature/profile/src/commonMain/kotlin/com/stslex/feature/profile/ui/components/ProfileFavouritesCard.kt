package com.stslex.feature.profile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.core.ui.theme.AppDimension

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProfileFavouritesCard(
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(AppDimension.Padding.medium),
        onClick = onClick
    ) {
        Column {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProfileFollowingCard(
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(AppDimension.Padding.medium),
        onClick = onClick
    ) {
        Column {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProfileFollowersCard(
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(AppDimension.Padding.medium),
        onClick = onClick
    ) {
        Column {
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