package com.stslex.wizard.feature.match.data.repository

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.feature.match.data.model.MatchDataModel

interface MatchRepository {

    suspend fun getMatches(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<MatchDataModel>
}