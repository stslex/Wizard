package com.stslex.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.base.rememberStore
import com.stslex.feature.home.ui.store.HomeScreenStore
import com.stslex.feature.home.ui.store.HomeScreenStoreComponent
import com.stslex.feature.home.ui.store.HomeScreenStoreComponent.Action

object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val store = rememberStore<HomeScreenStore>()
        val state by remember { store.state }.collectAsState()

        HomeScreenContent(
            state = state,
            sendAction = store::sendAction
        )
    }
}

@Composable
private fun HomeScreenContent(
    state: HomeScreenStoreComponent.State,
    sendAction: (Action) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val defaultGreetState = "Hello World!"
        val clickedGreetState = "Compose: ${state.text}"
        var isClicked by remember { mutableStateOf(false) }
        val greetingText by remember {
            derivedStateOf {
                if (isClicked) {
                    clickedGreetState
                } else {
                    defaultGreetState
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    isClicked = !isClicked
                }
            ) {
                Text(greetingText)
            }
            AnimatedVisibility(isClicked) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Button(
                onClick = {
                    sendAction(Action.OnClick)
                }
            ) {
                Text("second screen")
            }
        }
    }
}