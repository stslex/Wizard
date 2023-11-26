package com.stslex.feature.feed.data.repository

import com.stslex.core.core.Logger
import com.stslex.feature.feed.data.model.FeedDataModel
import com.stslex.feature.feed.data.model.FilmDataModel
import kotlinx.coroutines.delay

class MockFeedRepositoryImpl : FeedRepository {

    override suspend fun getFeed(
        page: Int,
        pageSize: Int
    ): FeedDataModel {
        Logger.debug("getFeed page: $page, pageSize: $pageSize")
        delay(3000)
        return FeedDataModel(
            films = Array(pageSize) { index ->
                val itemIndex = page.dec() * pageSize + index
                FilmDataModel(
                    id = itemIndex.toString(),
                    title = "Title $itemIndex",
                    description = "Description $itemIndex",
                    imageUrl = "https://picsum.photos/200/300"
                )
            }.toList(),
            hasNextPage = true
        )
    }
}