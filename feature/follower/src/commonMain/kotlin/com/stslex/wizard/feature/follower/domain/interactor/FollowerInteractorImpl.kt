package com.stslex.wizard.feature.follower.domain.interactor

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.feature.follower.data.model.FollowerDataModel
import com.stslex.wizard.feature.follower.data.repository.FollowerRepository

class FollowerInteractorImpl(
    private val repository: FollowerRepository,
) : FollowerInteractor {

    override suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FollowerDataModel> = repository
        .getFollowers(
            uuid = uuid,
            query = query,
            page = page,
            pageSize = pageSize
        )

    override suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FollowerDataModel> = repository
        .getFollowing(
            uuid = uuid,
            query = query,
            page = page,
            pageSize = pageSize
        )
}