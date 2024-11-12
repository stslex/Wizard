package com.stslex.wizard.core.network.clients.match.client

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.network.clients.match.model.request.MatchCreateRequest
import com.stslex.wizard.core.network.clients.match.model.response.MatchDetailResponse
import com.stslex.wizard.core.network.clients.match.model.response.MatchResponse
import com.stslex.wizard.core.network.clients.match.model.response.MatchStatusResponse
import com.stslex.wizard.core.network.clients.match.model.response.MatchUserResponse
import com.stslex.wizard.core.network.model.PagingRequest
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock

class MockMatchClientImpl : MatchClient {

    private val matches = MutableList(800) {
        createMatch(it)
    }

    override suspend fun getMatches(
        request: PagingRequest
    ): PagingResponse<MatchResponse> {
        val page = request.page
        val pageSize = request.pageSize
        val startIndex = page * pageSize
        val endIndex = (startIndex + pageSize).coerceAtMost(matches.size.dec())
        val data = matches.subList(startIndex, endIndex)
        delay(1000)
        return PagingResponse(
            page = page,
            pageSize = pageSize,
            result = data.map {
                MatchResponse(
                    uuid = it.uuid,
                    title = it.title,
                    description = it.description,
                    status = it.status,
                    participants = it.participants,
                    creatorUuid = "creatorUuid",
                    coverUrl = "coverUrl",
                    expiresAt = it.expiresAt
                )
            },
            total = matches.size,
            hasMore = endIndex < matches.size
        )
    }

    override suspend fun getMatch(
        matchUuid: String
    ): MatchDetailResponse {
        delay(1000)
        return matches.first { it.uuid == matchUuid }
    }

    override suspend fun createMatch(
        request: MatchCreateRequest
    ): MatchDetailResponse {
        delay(1000)
        val createdMatch = createMatch(matches.size)
        matches.add(createdMatch)
        return createdMatch
    }

    private fun createMatch(index: Int): MatchDetailResponse {
        val created = Clock.System.now().epochSeconds
        val updated = created + 1000 * 60 * 60
        val expires = created + 1000 * 60 * 60 * 24
        return MatchDetailResponse(
            uuid = "uuid$index",
            title = "title$index",
            description = "description$index",
            status = if (index % 2 == 0) MatchStatusResponse.PENDING else MatchStatusResponse.ACTIVE,
            participants = createMatchUsers(index),
            creatorUuid = "creatorUuid",
            createdAt = created,
            updatedAt = updated,
            expiresAt = expires,
            coverUrl = "coverUrl"
        )
    }

    private fun createMatchUsers(index: Int): List<MatchUserResponse> =
        List((index * 2) % 10) {
            MatchUserResponse(
                uuid = "uuid$it",
                avatar = "avatar$it",
                username = "username$it",
                isCreator = it == 0,
                isAccepted = it % 2 == 0
            )
        }
}