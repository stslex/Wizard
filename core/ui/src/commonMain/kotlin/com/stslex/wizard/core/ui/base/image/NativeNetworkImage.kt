package com.stslex.wizard.core.ui.base.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
expect fun NativeNetworkImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
)
