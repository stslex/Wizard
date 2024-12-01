package com.stslex.wizard.core.network.api.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.network.api.api.kinopoisk.api.KinopoiskApiClient
import com.stslex.wizard.core.network.api.api.kinopoisk.api.KinopoiskApiClientImpl
import com.stslex.wizard.core.network.api.api.kinopoisk.source.KinopoiskNetworkClient
import com.stslex.wizard.core.network.api.api.kinopoisk.source.KinopoiskNetworkClientImpl
import com.stslex.wizard.core.network.api.api.server.client.ServerApiClient
import com.stslex.wizard.core.network.api.api.server.client.ServerApiClientImpl
import com.stslex.wizard.core.network.api.api.server.error_handler.ErrorHandler
import com.stslex.wizard.core.network.api.api.server.error_handler.ErrorHandlerImpl
import com.stslex.wizard.core.network.api.api.server.http_client.ServerHttpClient
import com.stslex.wizard.core.network.api.api.server.http_client.ServerHttpClientImpl
import com.stslex.wizard.core.network.api.clients.auth.client.AuthClient
import com.stslex.wizard.core.network.api.clients.auth.client.AuthClientImpl
import com.stslex.wizard.core.network.api.clients.film.client.FilmClient
import com.stslex.wizard.core.network.api.clients.film.client.MockFilmClientImpl
import com.stslex.wizard.core.network.api.clients.match.client.MatchClient
import com.stslex.wizard.core.network.api.clients.match.client.MockMatchClientImpl
import com.stslex.wizard.core.network.api.clients.profile.client.ProfileClient
import com.stslex.wizard.core.network.api.clients.profile.client.ProfileClientImpl
import com.stslex.wizard.core.network.api.utils.token.AuthController
import com.stslex.wizard.core.network.api.utils.token.AuthControllerImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleCoreNetworkApi : AppModule() {

    override fun declaration(): ModuleDeclaration = {

        /*Clients*/
        single<ErrorHandler> {
            ErrorHandlerImpl(
                client = lazy { get<ServerHttpClient>() },
                tokenProvider = get()
            )
        }
        singleOf(::ServerHttpClientImpl) { bind<ServerHttpClient>() }

        /*Kinopoisk Api*/
        singleOf(::KinopoiskApiClientImpl) { bind<KinopoiskApiClient>() }
        singleOf(::KinopoiskNetworkClientImpl) { bind<KinopoiskNetworkClient>() }

        /*Server Api*/
        singleOf(::ServerApiClientImpl) { bind<ServerApiClient>() }

        /*Clients*/
        singleOf(::AuthClientImpl) { bind<AuthClient>() }
        singleOf(::MockFilmClientImpl) { bind<FilmClient>() }
        // todo remove mock
//        singleOf(::FilmClientImpl) { bind<FilmClient>() }
        singleOf(::ProfileClientImpl) { bind<ProfileClient>() }
        singleOf(::MockMatchClientImpl) { bind<MatchClient>() }

        /*Utils*/
        singleOf(::AuthControllerImpl) { bind<AuthController>() }
    }
}
