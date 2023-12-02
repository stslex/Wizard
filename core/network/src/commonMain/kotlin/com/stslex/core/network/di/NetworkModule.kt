package com.stslex.core.network.di

import com.stslex.core.network.client.base.DefaultNetworkClientImpl
import com.stslex.core.network.client.base.NetworkClient
import com.stslex.core.network.client.kinopoisk.KinopoiskClient
import com.stslex.core.network.client.kinopoisk.KinopoiskClientImpl
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.core.network.clients.film.client.MockFilmClientImpl
import com.stslex.core.network.clients.profile.client.MockProfileClientImpl
import com.stslex.core.network.clients.profile.client.ProfileClient
import org.koin.dsl.module

val networkModule = module {
    single<NetworkClient> { DefaultNetworkClientImpl(dispatcher = get()) }
    single<KinopoiskClient> { KinopoiskClientImpl(appDispatcher = get()) }
    single<FilmClient> { MockFilmClientImpl() }
    single<ProfileClient> { MockProfileClientImpl() }
}