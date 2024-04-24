package com.stslex.core.network.clients.match.client

import com.stslex.core.core.paging.PagingResponse
import com.stslex.core.network.api.base.get
import com.stslex.core.network.api.base.post
import com.stslex.core.network.api.base.requestPaging
import com.stslex.core.network.api.server.client.ServerApiClient
import com.stslex.core.network.clients.match.model.request.MatchCreateRequest
import com.stslex.core.network.clients.match.model.response.MatchDetailResponse
import com.stslex.core.network.clients.match.model.response.MatchResponse
import com.stslex.core.network.model.PagingRequest
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody

class MatchClientImpl(
    private val client: ServerApiClient
) : MatchClient {

    override suspend fun getMatches(
        request: PagingRequest
    ): PagingResponse<MatchResponse> = client.get("$HOST/list") {
        requestPaging(request)
    }

    override suspend fun getMatch(
        matchUuid: String
    ): MatchDetailResponse = client.get("$HOST/detail") {
        parameter(PARAMETER_MATCH_UUID, matchUuid)
    }

    override suspend fun createMatch(
        request: MatchCreateRequest
    ): MatchDetailResponse = client.post("$HOST/create") {
        setBody(request)
    }

    companion object {

        private const val HOST = "match"
        private const val PARAMETER_MATCH_UUID = "match_uuid"
    }
}