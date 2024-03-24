package com.stslex.feature.profile.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfileLogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "Logout",
            style = MaterialTheme.typography.titleMedium
        )
    }
}