package com.stslex.feature.film_feed.data.repository

import com.stslex.feature.film_feed.data.model.FeedDataModel

interface FeedRepository {

    suspend fun getFeed(page: Int, pageSize: Int): FeedDataModel
}
