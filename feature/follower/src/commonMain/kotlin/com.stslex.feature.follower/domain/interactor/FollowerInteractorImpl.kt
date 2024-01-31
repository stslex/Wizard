package com.stslex.feature.follower.domain.interactor

import com.stslex.feature.follower.data.repository.FollowerRepository
import com.stslex.feature.follower.domain.model.toUI
import com.stslex.feature.follower.ui.model.FollowerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FollowerInteractorImpl(
    private val repository: FollowerRepository
) : FollowerInteractor {

    override fun getFollowers(
        uuid: String,
        page: Int, pageSize: Int
    ): Flow<List<FollowerModel>> = repository
        .getFollowers(
            uuid = uuid,
            page = page,
            pageSize = pageSize
        )
        .map { it.toUI() }

    override fun getFollowing(
        uuid: String,
        page: Int,
        pageSize: Int
    ): Flow<List<FollowerModel>> = repository
        .getFollowing(
            uuid = uuid,
            page = page,
            pageSize = pageSize
        )
        .map { it.toUI() }
}