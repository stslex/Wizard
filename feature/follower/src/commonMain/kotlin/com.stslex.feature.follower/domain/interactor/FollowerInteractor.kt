package com.stslex.feature.follower.domain.interactor

import com.stslex.core.core.paging.PagingResponse
import com.stslex.feature.follower.data.model.FollowerDataModel

interface FollowerInteractor {

    suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FollowerDataModel>

    suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FollowerDataModel>
}

