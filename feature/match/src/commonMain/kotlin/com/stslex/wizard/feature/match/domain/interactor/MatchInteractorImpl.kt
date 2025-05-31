package com.stslex.wizard.feature.match.domain.interactor

import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.wizard.core.core.paging.pagingMap
import com.stslex.wizard.core.network.api.utils.token.AuthController
import com.stslex.wizard.feature.match.data.repository.MatchRepository
import com.stslex.wizard.feature.match.di.MatchScope
import com.stslex.wizard.feature.match.domain.model.MatchDomainModel
import com.stslex.wizard.feature.match.domain.model.toDomain
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchScope::class)
@Scoped
internal class MatchInteractorImpl(
    private val repository: MatchRepository,
    private val authController: AuthController
) : MatchInteractor {

    override suspend fun getMatches(
        uuid: String,
        query: String,
        page: Int,
        pageSize: Int,
    ): PagingResponse<MatchDomainModel> = repository
        .getMatches(
            uuid = uuid,
            page = page,
            pageSize = pageSize,
            query = query
        )
        .pagingMap {
            it.toDomain()
        }

    override suspend fun logout() {
        authController.logout()
    }
}