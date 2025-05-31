package com.stslex.wizard.feature.match.domain.interactor

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.feature.match.domain.model.MatchDomainModel

internal interface MatchInteractor {

    suspend fun getMatches(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int,
    ): PagingResponse<MatchDomainModel>

    suspend fun logout()
}
