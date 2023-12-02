package com.stslex.core.network.di

import com.stslex.core.network.api.base.DefaultNetworkClientImpl
import com.stslex.core.network.api.base.NetworkClient
import com.stslex.core.network.api.kinopoisk.KinopoiskApiClient
import com.stslex.core.network.api.kinopoisk.KinopoiskApiClientImpl
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.core.network.clients.film.client.MockFilmClientImpl
import com.stslex.core.network.clients.profile.client.MockProfileClientImpl
import com.stslex.core.network.clients.profile.client.ProfileClient
import org.koin.dsl.module

val coreNetworkModule = module {
    single<NetworkClient> { DefaultNetworkClientImpl(dispatcher = get()) }
    single<KinopoiskApiClient> { KinopoiskApiClientImpl(appDispatcher = get()) }
    single<FilmClient> { MockFilmClientImpl() }
    single<ProfileClient> { MockProfileClientImpl() }
}