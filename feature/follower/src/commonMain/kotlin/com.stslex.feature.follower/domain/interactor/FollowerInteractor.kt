package com.stslex.feature.follower.domain.interactor

import com.stslex.feature.follower.ui.model.FollowerModel
import kotlinx.coroutines.flow.StateFlow

interface FollowerInteractor {

    val followItems: StateFlow<List<FollowerModel>>

    suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    )

    suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    )
}

