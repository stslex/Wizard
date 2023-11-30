package com.stslex.feature.film_feed.domain.interactor

import com.stslex.feature.film_feed.domain.model.FeedDomainModel

interface FeedInteractor {

    suspend fun getFeed(page: Int, pageSize: Int): FeedDomainModel
}
