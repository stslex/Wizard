package com.stslex.feature.feed.data.repository

import com.stslex.feature.feed.data.model.FeedDataModel
import com.stslex.feature.feed.data.model.FilmDataModel

class MockFeedRepositoryImpl : FeedRepository {

    override suspend fun getFeed(
        page: Int,
        pageSize: Int
    ): FeedDataModel {
        return FeedDataModel(
            films = Array(pageSize) { index ->
                FilmDataModel(
                    id = page + index,
                    title = "Title $index",
                    description = "Description $index",
                    imageUrl = "https://picsum.photos/200/300"
                )
            }.toList(),
            hasNextPage = true
        )
    }
}