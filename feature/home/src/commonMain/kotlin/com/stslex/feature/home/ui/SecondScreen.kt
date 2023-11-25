package com.stslex.feature.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

data class SecondScreen(
    private val text: String
) : Screen {

    @Composable
    override fun Content() {
        Text(text = "Second Screen: $text")
    }
}