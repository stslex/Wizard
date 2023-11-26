package com.stslex.feature.feed.domain.interactor

import com.stslex.feature.feed.domain.model.FeedDomainModel

interface FeedInteractor {

    suspend fun getFeed(page: Int, pageSize: Int): FeedDomainModel
}
