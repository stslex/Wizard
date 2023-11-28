package com.stslex.core.network.di

import com.stslex.core.network.client.NetworkClient
import com.stslex.core.network.client.NetworkClientImpl
import com.stslex.core.network.clients.film.client.FilmClient
import com.stslex.core.network.clients.film.client.MockFilmClientImpl
import com.stslex.core.network.clients.profile.client.MockProfileClientImpl
import com.stslex.core.network.clients.profile.client.ProfileClient
import org.koin.dsl.module

val networkModule = module {
    single<NetworkClient> {
        NetworkClientImpl(appDispatcher = get())
    }
    single<FilmClient> { MockFilmClientImpl() }
    single<ProfileClient> { MockProfileClientImpl() }
}