package com.stslex.feature.follower.domain.interactor

import com.stslex.core.network.utils.PagingWorker
import com.stslex.feature.follower.data.model.FollowerDataModel
import com.stslex.feature.follower.data.repository.FollowerRepository
import com.stslex.feature.follower.domain.model.toUI
import com.stslex.feature.follower.ui.model.FollowerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FollowerInteractorImpl(
    private val repository: FollowerRepository,
    private val pagingWorker: PagingWorker
) : FollowerInteractor {

    private val _followItems: MutableStateFlow<List<FollowerModel>> = MutableStateFlow(emptyList())
    override val followItems: StateFlow<List<FollowerModel>> = _followItems.asStateFlow()

    override suspend fun getFollowers(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ) {
        pagingWorker {
            repository
                .getFollowers(
                    uuid = uuid,
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
                .let { items ->
                    onItemsLoaded(items)
                }
        }
    }

    override suspend fun getFollowing(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ) {
        pagingWorker {
            repository
                .getFollowing(
                    uuid = uuid,
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
                .let { items ->
                    onItemsLoaded(items)
                }
        }
    }

    private suspend fun onItemsLoaded(data: List<FollowerDataModel>) {
        val items = data.map { it.toUI() }
        val screenItems = followItems
            .replayCache
            .lastOrNull()
            .orEmpty()
            .plus(items)
        _followItems.emit(screenItems)
    }
}