package com.stslex.wizard.feature.match.data.repository

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.core.paging.pagingMap
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.network.clients.match.client.MatchClient
import com.stslex.core.network.model.PagingRequest
import com.stslex.feature.match.data.model.MatchDataModel
import com.stslex.feature.match.data.model.toData

class MatchRepositoryImpl(
    private val client: MatchClient,
    private val userStore: UserStore
) : MatchRepository {

    override suspend fun getMatches(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<MatchDataModel> = client
        .getMatches(
            request = PagingRequest(
                uuid = uuid,
                page = page,
                pageSize = pageSize,
                query = query
            )
        )
        .pagingMap {
            it.toData(userUUID = userStore.uuid)
        }
}