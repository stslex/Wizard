package com.stslex.feature.follower.domain.interactor

import com.stslex.feature.follower.ui.model.FollowerModel
import kotlinx.coroutines.flow.Flow

interface FollowerInteractor {

    fun getFollowers(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FollowerModel>>

    fun getFollowing(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FollowerModel>>
}

