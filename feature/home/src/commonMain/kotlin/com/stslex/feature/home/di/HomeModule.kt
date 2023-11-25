package com.stslex.feature.home.di

import com.stslex.feature.home.domain.HomeInteractor
import com.stslex.feature.home.domain.HomeInteractorImpl
import org.koin.dsl.module

val homeModule = module {
    single<HomeInteractor> { HomeInteractorImpl() }
}