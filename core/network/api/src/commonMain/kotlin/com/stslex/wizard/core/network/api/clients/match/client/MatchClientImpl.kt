package com.stslex.wizard.core.network.api.clients.match.client

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.network.api.api.base.get
import com.stslex.wizard.core.network.api.api.base.post
import com.stslex.wizard.core.network.api.api.base.requestPaging
import com.stslex.wizard.core.network.api.api.server.client.ServerApiClient
import com.stslex.wizard.core.network.api.clients.match.model.request.MatchCreateRequest
import com.stslex.wizard.core.network.api.clients.match.model.response.MatchDetailResponse
import com.stslex.wizard.core.network.api.clients.match.model.response.MatchResponse
import com.stslex.wizard.core.network.api.model.PagingRequest
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