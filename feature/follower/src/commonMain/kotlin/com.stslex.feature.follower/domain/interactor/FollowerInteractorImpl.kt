package com.stslex.feature.follower.domain.interactor

import com.stslex.core.core.paging.PagingResponse
import com.stslex.feature.follower.data.model.FollowerDataModel
import com.stslex.feature.follower.data.repository.FollowerRepository

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