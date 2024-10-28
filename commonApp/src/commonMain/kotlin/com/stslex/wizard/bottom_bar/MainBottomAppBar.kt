package com.stslex.wizard.bottom_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.navigation.Screen

@Composable
fun MainBottomAppBar(
    onBottomAppBarClick: (Screen) -> Unit,
    currentDestination: Screen?
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        BottomAppBarResource
            .entries
            .forEach { item ->
                val isSelected = currentDestination == item.screen
                BottomAppBarItem(
                    item = item,
                    isSelected = isSelected,
                    onBottomAppBarClick = remember(item) {
                        onBottomAppBarClick
                    }
                )
            }
    }
}

@Composable
private fun RowScope.BottomAppBarItem(
    item: BottomAppBarResource,
    isSelected: Boolean,
    onBottomAppBarClick: (Screen) -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            onBottomAppBarClick(item.screen)
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