package com.stslex.wizard.core.network.api.clients.match.client

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.network.api.clients.match.model.request.MatchCreateRequest
import com.stslex.wizard.core.network.api.clients.match.model.response.MatchDetailResponse
import com.stslex.wizard.core.network.api.clients.match.model.response.MatchResponse
import com.stslex.wizard.core.network.api.model.PagingRequest

interface MatchClient {

    suspend fun getMatches(request: PagingRequest): PagingResponse<MatchResponse>

    suspend fun getMatch(matchUuid: String): MatchDetailResponse

    suspend fun createMatch(request: MatchCreateRequest): MatchDetailResponse
}
