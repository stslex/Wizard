package com.stslex.feature.feed.data.repository

import com.stslex.feature.feed.data.model.FeedDataModel

interface FeedRepository {

    suspend fun getFeed(page: Int, pageSize: Int): FeedDataModel
}
