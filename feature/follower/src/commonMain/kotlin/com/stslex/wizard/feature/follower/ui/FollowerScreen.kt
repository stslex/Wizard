package com.stslex.wizard.feature.follower.ui

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.follower.di.FollowerFeature
import com.stslex.wizard.feature.follower.mvi.handlers.FollowerComponent

@Composable
fun FollowerScreen(component: FollowerComponent) {
    NavComponentScreen(FollowerFeature, component) { processor ->
        FollowerScreenWidget(
            state = processor.state.value,
            onAction = processor::consume
        )
    }
}