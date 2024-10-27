package com.stslex.wizard.feature.favourite.ui.components.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.theme.AppDimension


@Composable
internal fun FavouriteScreenSearchField(
    query: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier
            .padding(AppDimension.Padding.medium)
            .fillMaxWidth()
            .padding(AppDimension.Padding.medium),
        value = query,
        onValueChange = onSearch,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = androidx.compose.ui.text.input.ImeAction.Search
        ),
    )
}
