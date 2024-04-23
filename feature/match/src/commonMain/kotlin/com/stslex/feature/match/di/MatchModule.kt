package com.stslex.feature.match.di

import com.stslex.feature.match.data.repository.MatchRepository
import com.stslex.feature.match.data.repository.MatchRepositoryImpl
import com.stslex.feature.match.domain.interactor.MatchInteractor
import com.stslex.feature.match.domain.interactor.MatchInteractorImpl
import org.koin.dsl.module

val featureMatchModule = module {
    single<MatchRepository> { MatchRepositoryImpl(client = get()) }
    factory<MatchInteractor> { MatchInteractorImpl(repository = get()) }
}