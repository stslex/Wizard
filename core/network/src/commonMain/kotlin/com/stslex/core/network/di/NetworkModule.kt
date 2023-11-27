package com.stslex.core.network.di

import com.stslex.core.network.client.NetworkClient
import com.stslex.core.network.client.NetworkClientImpl
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.core.network.clients.film.client.MockFilmClientImpl
import org.koin.dsl.module

val networkModule = module {
    single<NetworkClient> {
        NetworkClientImpl(appDispatcher = get())
    }
    single<FilmClient> { MockFilmClientImpl() }
}