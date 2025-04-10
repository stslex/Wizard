package com.stslex.wizard.bottom_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.navigation.Config

@Composable
internal fun MainBottomAppBar(
    onBottomAppBarClick: (Config.BottomBar) -> Unit,
    currentDestination: Config?
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        BottomAppBarResource
            .entries
            .forEach { item ->
                BottomAppBarItem(
                    item = item,
                    isSelected = currentDestination == item.config,
                    onBottomAppBarClick = onBottomAppBarClick
                )
            }
    }
}

@Composable
private fun RowScope.BottomAppBarItem(
    item: BottomAppBarResource,
    isSelected: Boolean,
    onBottomAppBarClick: (Config.BottomBar) -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            onBottomAppBarClick(item.config)
        },
        icon = {
            Icon(
                imageVector = item.getIcon(isSelected),
                contentDescription = null
            )
        },
        label = {
            Text(text = item.title)
        },
        alwaysShowLabel = false
    )
}