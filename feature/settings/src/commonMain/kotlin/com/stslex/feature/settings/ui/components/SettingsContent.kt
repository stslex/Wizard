package com.stslex.feature.settings.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.settings.ui.model.SettingsColumnItems

@Composable
fun SettingsContent(
    logOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            count = SettingsColumnItems.entries.size,
            key = { index -> SettingsColumnItems.entries[index].ordinal },
            contentType = { "SettingsColumnItems" }
        ) { index ->
            SettingsColumnItems.entries.getOrNull(index)?.let { item ->
                SettingsColumnItem(
                    title = item.title,
                    iconVector = item.icon,
                    onClick = {
                        when (item) {
                            SettingsColumnItems.LOG_OUT -> logOut()
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun SettingsColumnItem(
    title: String,
    iconVector: ImageVector?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        modifier = modifier
            .padding(AppDimension.Padding.medium)
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(AppDimension.Radius.medium)
    ) {
        iconVector?.let { icon ->
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.align(Alignment.CenterVertically),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(AppDimension.Padding.medium))
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}