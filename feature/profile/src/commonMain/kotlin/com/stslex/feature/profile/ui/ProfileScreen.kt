package com.stslex.feature.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.stslex.feature.profile.ui.model.ProfileModel
import com.stslex.feature.profile.ui.store.ProfileScreenState
import com.stslex.feature.profile.ui.store.ProfileStore
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Action
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.State

object ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val store = getScreenModel<ProfileStore>()
        LaunchedEffect(Unit) {
            store.sendAction(Action.LoadProfile("uuid"))
        }
        val state by remember { store.state }.collectAsState()
        ProfileScreenContent(state)
    }
}

@Composable
private fun ProfileScreenContent(state: State) {
    when (val screen = state.screen) {
        is ProfileScreenState.Content -> ProfileScreenContent(screen.model)
        is ProfileScreenState.Error -> ProfileScreenError(screen.error)
        ProfileScreenState.Loading -> ProfileScreenLoading()
    }
}

@Composable
internal fun ProfileScreenLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading")
        CircularProgressIndicator()
    }
}

@Composable
internal fun ProfileScreenError(
    error: Throwable? = null,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: ${error?.message}")
    }
}

@Composable
internal fun ProfileScreenContent(
    profile: ProfileModel,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = profile.username
    )
}