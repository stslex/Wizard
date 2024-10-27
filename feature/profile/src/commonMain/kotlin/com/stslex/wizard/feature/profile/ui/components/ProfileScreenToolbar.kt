package com.stslex.wizard.feature.profile.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.components.AppToolbar

@Composable
fun ProfileScreenToolbar(
    nickname: String,
    isCurrentUser: Boolean,
    onSettingsClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppToolbar(
        modifier = modifier,
        title = nickname,
        onBackClick = onBackClick.takeIf { isCurrentUser.not() },
        isActionVisible = isCurrentUser,
        actionIcon = {
            IconButton(
                onClick = onSettingsClick
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    )
}