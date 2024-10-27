package com.stslex.wizard.feature.match.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.base.AppError
import com.stslex.wizard.core.ui.theme.AppDimension

@Composable
internal fun MatchScreenError(
    error: AppError,
    logOut: () -> Unit,
    repeatLastAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (error) {
                is AppError.AuthError -> {
                    Text(text = "Auth error: ${error.message}")
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    Button(onClick = logOut) {
                        Text(text = "logout")
                    }
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    Button(onClick = repeatLastAction) {
                        Text(text = "Retry")
                    }
                }

                is AppError.OtherError -> {
                    Text(text = "Error: ${error.message}")
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    Button(onClick = repeatLastAction) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}