package com.stslex.feature.feed.data.repository

import com.stslex.core.core.Logger
import com.stslex.feature.feed.data.model.FeedDataModel
import com.stslex.feature.feed.data.model.FilmDataModel
import kotlinx.coroutines.delay

// "https://picsum.photos/200/300"
class MockFeedRepositoryImpl : FeedRepository {

    private val url1 =
        "https://unsplash.com/photos/CiUR8zISX60/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MTV8fGNpbmVtYXxlbnwwfHx8fDE3MDEwMTY0OTZ8MA&force=true&w=640"
    private val url2 =
        "https://unsplash.com/photos/Geepgu8bCas/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MjJ8fGNpbmVtYXxlbnwwfHx8fDE3MDEwMTY1MDN8MA&force=true&w=640"
    private val url3 =
        "https://unsplash.com/photos/h5cFbbecEuY/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MzF8fGNpbmVtYXxlbnwwfHx8fDE3MDEwMTY1MDN8MA&force=true&w=640"

    override suspend fun getFeed(
        page: Int,
        pageSize: Int
    ): FeedDataModel {
        Logger.debug("getFeed page: $page, pageSize: $pageSize")
        delay(1000)
        return FeedDataModel(
            films = Array(pageSize) { index ->
                val itemIndex = page.dec() * pageSize + index
                FilmDataModel(
                    id = itemIndex.toString(),
                    title = "Title $itemIndex",
                    description = "Description $itemIndex",
                    imageUrl = when (itemIndex % 3) {
                        0 -> url1
                        1 -> url2
                        else -> url3
                    },

                    )
            }.toList(),
            hasNextPage = true
        )
    }
}