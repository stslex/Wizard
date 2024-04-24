package com.stslex.core.network.di

import com.stslex.core.network.api.kinopoisk.api.KinopoiskApiClient
import com.stslex.core.network.api.kinopoisk.api.KinopoiskApiClientImpl
import com.stslex.core.network.api.kinopoisk.source.KinopoiskNetworkClient
import com.stslex.core.network.api.kinopoisk.source.KinopoiskNetworkClientImpl
import com.stslex.core.network.api.server.client.ServerApiClient
import com.stslex.core.network.api.server.client.ServerApiClientImpl
import com.stslex.core.network.api.server.error_handler.ErrorHandler
import com.stslex.core.network.api.server.error_handler.ErrorHandlerImpl
import com.stslex.core.network.api.server.http_client.ServerHttpClient
import com.stslex.core.network.api.server.http_client.ServerHttpClientImpl
import com.stslex.core.network.clients.auth.client.AuthClient
import com.stslex.core.network.clients.auth.client.AuthClientImpl
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.core.network.clients.film.client.MockFilmClientImpl
import com.stslex.core.network.clients.match.client.MatchClient
import com.stslex.core.network.clients.match.client.MockMatchClientImpl
import com.stslex.core.network.clients.profile.client.ProfileClient
import com.stslex.core.network.clients.profile.client.ProfileClientImpl
import com.stslex.core.network.utils.PagingWorker
import com.stslex.core.network.utils.PagingWorkerImpl
import com.stslex.core.network.utils.token.AuthController
import com.stslex.core.network.utils.token.AuthControllerImpl
import org.koin.dsl.module

val coreNetworkModule = module {
    /*Clients*/
    single<ErrorHandler> {
        ErrorHandlerImpl(
            client = lazy { get<ServerHttpClient>() },
            tokenProvider = get()
        )
    }

    single<ServerHttpClient> {
        ServerHttpClientImpl(
            errorHandler = get(),
            tokenProvider = get()
        )
    }

    /*Kinopoisk Api*/
    single<KinopoiskApiClient> { KinopoiskApiClientImpl(appDispatcher = get()) }
    single<KinopoiskNetworkClient> { KinopoiskNetworkClientImpl(apiClient = get()) }

    /*Server Api*/
    single<ServerApiClient> {
        ServerApiClientImpl(
            appDispatcher = get(),
            client = get()
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
        // todo remove mock
        MockFilmClientImpl()
//        FilmClientImpl(
//            client = get(),
//            kinopoiskClient = get()
//        )
    }
    single<ProfileClient> {
        ProfileClientImpl(client = get())
    }
    single<MatchClient> {
        // todo remove mock
        MockMatchClientImpl()
    }

    /*Utils*/
    single<AuthController> {
        AuthControllerImpl(
            userStore = get(),
        )
    }

    factory<PagingWorker> {
        PagingWorkerImpl()
    }
}