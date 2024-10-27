package com.stslex.wizard.feature.film_feed.domain.interactor

import com.stslex.wizard.feature.film_feed.domain.model.FeedDomainModel

interface FeedInteractor {

    suspend fun getFeed(page: Int, pageSize: Int): FeedDomainModel
}
