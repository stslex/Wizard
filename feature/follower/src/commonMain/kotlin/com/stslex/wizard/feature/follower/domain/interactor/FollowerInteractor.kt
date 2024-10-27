package com.stslex.wizard.feature.follower.domain.interactor

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.feature.follower.data.model.FollowerDataModel

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

