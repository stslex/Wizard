package com.stslex.core.network.di

import com.stslex.core.network.api.base.DefaultNetworkClientImpl
import com.stslex.core.network.api.base.NetworkClient
import com.stslex.core.network.api.kinopoisk.api.KinopoiskApiClient
import com.stslex.core.network.api.kinopoisk.api.KinopoiskApiClientImpl
import com.stslex.core.network.api.kinopoisk.source.KinopoiskNetworkClient
import com.stslex.core.network.api.kinopoisk.source.KinopoiskNetworkClientImpl
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.core.network.clients.film.client.FilmClientImpl
import com.stslex.core.network.clients.profile.client.MockProfileClientImpl
import com.stslex.core.network.clients.profile.client.ProfileClient
import org.koin.dsl.module

val coreNetworkModule = module {
    single<NetworkClient> { DefaultNetworkClientImpl(dispatcher = get()) }
    single<KinopoiskApiClient> { KinopoiskApiClientImpl(appDispatcher = get()) }
    single<KinopoiskNetworkClient> { KinopoiskNetworkClientImpl(apiClient = get()) }
    single<FilmClient> {
        FilmClientImpl(
            client = get(),
            kinopoiskClient = get()
        )
    }
    single<ProfileClient> { MockProfileClientImpl() }
}