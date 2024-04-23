package com.stslex.feature.match.domain.interactor

import com.stslex.core.core.paging.PagingResponse
import com.stslex.feature.match.domain.model.MatchDomainModel

interface MatchInteractor {

    suspend fun getMatches(
        uuid: String,
        page: Int,
        pageSize: Int,
    ): PagingResponse<MatchDomainModel>
}
