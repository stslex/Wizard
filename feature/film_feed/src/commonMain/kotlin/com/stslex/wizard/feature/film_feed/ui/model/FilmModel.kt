package com.stslex.wizard.feature.film_feed.ui.model

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.kit.pager.PagingUiItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Stable
data class FilmModel(
    override val id: String,
    val title: String,
    val imageUrl: String,
    val rate: String,
    val genres: ImmutableList<String>,
) : PagingUiItem {

    companion object {

        val Empty = FilmModel(
            id = "",
            title = "",
            imageUrl = "",
            rate = "",
            genres = emptyList<String>().toImmutableList()
        )
    }
}