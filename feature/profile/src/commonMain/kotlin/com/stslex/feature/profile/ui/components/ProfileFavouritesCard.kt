package com.stslex.feature.profile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.core.ui.theme.AppDimension

@OptIn(ExperimentalMaterial3Api::class)
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
        Column(
            modifier = Modifier.padding(AppDimension.Padding.medium),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
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
        Column(
            modifier = Modifier.padding(AppDimension.Padding.medium),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
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
        Column(
            modifier = Modifier.padding(AppDimension.Padding.medium),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
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