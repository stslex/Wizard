package com.stslex.feature.auth

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object AuthScreen : Screen {

    @Composable
    override fun Content() {
        Text("Auth")
    }
}