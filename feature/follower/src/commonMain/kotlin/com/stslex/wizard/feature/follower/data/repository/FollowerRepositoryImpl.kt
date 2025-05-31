package com.stslex.wizard.feature.follower.data.repository

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.core.paging.pagingMap
import com.stslex.wizard.core.network.api.clients.profile.client.ProfileClient
import com.stslex.wizard.core.network.api.clients.profile.model.request.PagingProfileRequest
import com.stslex.wizard.feature.follower.data.model.FollowerDataModel
import com.stslex.wizard.feature.follower.data.model.toData
import com.stslex.wizard.feature.follower.di.FollowerScope
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FollowerScope::class)
@Scoped
internal class FollowerRepositoryImpl(
    private val client: ProfileClient
) : FollowerRepository {

    override suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FollowerDataModel> = client
        .getFollowers(
            PagingProfileRequest(
                uuid = uuid,
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
        .pagingMap { it.toData() }


    override suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<FollowerDataModel> = client
        .getFollowing(
            PagingProfileRequest(
                uuid = uuid,
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
        .pagingMap { it.toData() }
}