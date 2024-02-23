package com.stslex.feature.follower.data.repository

import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.feature.follower.data.model.FollowerDataModel
import com.stslex.feature.follower.data.model.toData

class FollowerRepositoryImpl(
    private val client: ProfileClient
) : FollowerRepository {

    override suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): List<FollowerDataModel> = client
        .getFollowers(
            uuid = uuid,
            query = query,
            page = page,
            pageSize = pageSize
        )
        .toData()

    override suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): List<FollowerDataModel> = client
        .getFollowing(
            uuid = uuid,
            query = query,
            page = page,
            pageSize = pageSize
        )
        .toData()
}