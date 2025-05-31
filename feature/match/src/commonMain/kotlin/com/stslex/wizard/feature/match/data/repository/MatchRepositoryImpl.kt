package com.stslex.wizard.feature.match.data.repository

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.core.paging.pagingMap
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.network.api.clients.match.client.MatchClient
import com.stslex.wizard.core.network.api.model.PagingRequest
import com.stslex.wizard.feature.match.data.model.MatchDataModel
import com.stslex.wizard.feature.match.data.model.toData
import com.stslex.wizard.feature.match.di.MatchScope
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchScope::class)
@Scoped
internal class MatchRepositoryImpl(
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