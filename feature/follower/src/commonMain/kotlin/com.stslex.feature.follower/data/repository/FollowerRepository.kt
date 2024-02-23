package com.stslex.feature.follower.data.repository

import com.stslex.feature.follower.data.model.FollowerDataModel

interface FollowerRepository {

    suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): List<FollowerDataModel>

    suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): List<FollowerDataModel>
}

