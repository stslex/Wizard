package com.stslex.feature.feed.data.repository

import com.stslex.feature.feed.data.model.FeedDataModel

class FeedRepositoryImpl : FeedRepository {
    override suspend fun getFeed(page: Int, pageSize: Int): FeedDataModel {
        TODO("Not yet implemented")
    }

}

