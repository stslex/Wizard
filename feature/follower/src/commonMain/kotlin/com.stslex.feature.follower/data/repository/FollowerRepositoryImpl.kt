package com.stslex.feature.follower.data.repository

import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.feature.follower.data.model.FollowerDataModel
import com.stslex.feature.follower.data.model.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FollowerRepositoryImpl(
    private val client: ProfileClient
) : FollowerRepository {

    override fun getFollowers(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FollowerDataModel>> = flow {
        val result = client.getFollowers(
            uuid = uuid,
            page = page,
            pageSize = pageSize
        ).toData()
        emit(result)
    }

    override fun getFollowing(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FollowerDataModel>> = flow {
        val result = client.getFollowing(
            uuid = uuid,
            page = page,
            pageSize = pageSize
        ).toData()
        emit(result)
    }
}