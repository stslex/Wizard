package com.stslex.feature.feed.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.feature.feed.ui.model.FilmModel
import com.stslex.feature.feed.ui.model.ScreenState
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun FeedScreenContent(
    films: ImmutableList<FilmModel>,
    screenState: ScreenState.Content,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(
            count = films.size,
            key = films.key
        ) { index ->
            val film = films.getOrNull(index)
            if (film != null) {
                FeedScreenFilmItem(
                    modifier = Modifier,
                    film = film
                )
            }
        }

        if (screenState is ScreenState.Content.AppendLoading) {
            item {
                CircularProgressIndicator()
                // TODO add loading item
            }
        }
    }
}

private val ImmutableList<FilmModel>.key: ((Int) -> Any)?
    get() = if (isEmpty()) {
        null
    } else {
        { index ->
            get(index).id
        }
    }