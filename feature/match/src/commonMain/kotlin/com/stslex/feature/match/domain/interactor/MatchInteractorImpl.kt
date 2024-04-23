package com.stslex.feature.match.domain.interactor

import com.stslex.core.core.paging.PagingResponse
import com.stslex.core.core.paging.pagingMap
import com.stslex.feature.match.data.repository.MatchRepository
import com.stslex.feature.match.domain.model.MatchDomainModel
import com.stslex.feature.match.domain.model.toDomain

class MatchInteractorImpl(
    private val repository: MatchRepository,
) : MatchInteractor {

    override suspend fun getMatches(
        uuid: String,
        page: Int,
        pageSize: Int,
    ): PagingResponse<MatchDomainModel> = repository
        .getMatches(
            uuid = uuid,
            page = page,
            pageSize = pageSize,
            query = "" // todo add query
        )
        .pagingMap {
            it.toDomain()
        }
}