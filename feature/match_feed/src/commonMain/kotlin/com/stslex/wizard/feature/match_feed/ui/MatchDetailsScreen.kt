package com.stslex.wizard.feature.match_feed.ui

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.match_feed.di.MatchDetailsFeature
import com.stslex.wizard.feature.match_feed.ui.mvi.handlers.MatchDetailsComponent

@Composable
fun MatchDetailsScreen(component: MatchDetailsComponent) {
    NavComponentScreen(MatchDetailsFeature, component) { processor ->
        MatchDetailsScreenWidget(
            state = processor.state.value,
            sendAction = processor::consume
        )
    }
}
