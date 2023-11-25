package com.stslex.feature.feed.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.feature.feed.ui.model.FilmModel

@Composable
internal fun FeedScreenFilmItem(
    modifier: Modifier = Modifier,
    film: FilmModel
) {
    Column(
        modifier = modifier
    ) {
        Text(text = film.title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = film.description)
    }
}