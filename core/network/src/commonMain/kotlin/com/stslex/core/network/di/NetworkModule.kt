package com.stslex.core.network.di

import com.stslex.core.network.api.kinopoisk.api.KinopoiskApiClient
import com.stslex.core.network.api.kinopoisk.api.KinopoiskApiClientImpl
import com.stslex.core.network.api.kinopoisk.source.KinopoiskNetworkClient
import com.stslex.core.network.api.kinopoisk.source.KinopoiskNetworkClientImpl
import com.stslex.core.network.api.server.ServerApiClient
import com.stslex.core.network.api.server.ServerApiClientImpl
import com.stslex.core.network.clients.auth.client.AuthClient
import com.stslex.core.network.clients.auth.client.AuthClientImpl
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.core.network.clients.film.client.MockFilmClientImpl
import com.stslex.core.network.clients.profile.client.MockProfileClientImpl
import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.core.network.utils.token.AuthController
import com.stslex.core.network.utils.token.AuthControllerImpl
import org.koin.dsl.module

val coreNetworkModule = module {
    /*Kinopoisk Api*/
    single<KinopoiskApiClient> { KinopoiskApiClientImpl(appDispatcher = get()) }
    single<KinopoiskNetworkClient> { KinopoiskNetworkClientImpl(apiClient = get()) }

    /*Server Api*/
    single<ServerApiClient> {
        ServerApiClientImpl(
            appDispatcher = get(),
            tokenProvider = get()
        )
    }

    /*Clients*/
    single<AuthClient> {
        AuthClientImpl(
            networkClient = get(),
            tokenController = get()
        )
    }
    single<FilmClient> {
        MockFilmClientImpl()
//        FilmClientImpl(
//            client = get(),
//            kinopoiskClient = get()
//        )
    }
    single<ProfileClient> { MockProfileClientImpl() }

    /*Utils*/
    single<AuthController> {
        AuthControllerImpl(
            userStore = get(),
        )
    }
}