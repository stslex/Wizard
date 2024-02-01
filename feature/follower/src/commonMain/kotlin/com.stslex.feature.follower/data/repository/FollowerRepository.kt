package com.stslex.feature.follower.data.repository

import com.stslex.feature.follower.data.model.FollowerDataModel
import kotlinx.coroutines.flow.Flow

interface FollowerRepository {

    fun getFollowers(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FollowerDataModel>>

    fun getFollowing(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FollowerDataModel>>
}

