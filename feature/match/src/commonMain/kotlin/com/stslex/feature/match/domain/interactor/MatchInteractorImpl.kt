package com.stslex.feature.match.domain.interactor

import com.stslex.core.core.paging.PagingResponse
import com.stslex.core.core.paging.pagingMap
import com.stslex.core.network.utils.token.AuthController
import com.stslex.feature.match.data.repository.MatchRepository
import com.stslex.feature.match.domain.model.MatchDomainModel
import com.stslex.feature.match.domain.model.toDomain

class MatchInteractorImpl(
    private val repository: MatchRepository,
    private val authController: AuthController
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

    override suspend fun logout() {
        authController.logOut()
    }
}