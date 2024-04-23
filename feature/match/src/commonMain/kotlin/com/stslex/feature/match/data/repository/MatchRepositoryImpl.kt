package com.stslex.feature.match.data.repository

import com.stslex.core.network.clients.match.client.MatchClient
import com.stslex.core.network.model.PagingRequest
import com.stslex.feature.match.data.model.toData

class MatchRepositoryImpl(
    private val client: MatchClient
) : MatchRepository {

    override suspend fun getMatches(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): MatchDataPagingResult = client
        .getMatches(
            request = PagingRequest(
                uuid = uuid,
                page = page,
                pageSize = pageSize,
                query = query
            )
        )
        .toData()
}