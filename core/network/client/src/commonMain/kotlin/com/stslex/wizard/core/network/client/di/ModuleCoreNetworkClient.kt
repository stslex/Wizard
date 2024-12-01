package com.stslex.wizard.core.network.client.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.network.client.AppHttpClient
import io.ktor.client.HttpClient
import org.koin.core.annotation.Module
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleCoreNetworkClient : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        single<HttpClient> { AppHttpClient.HttpClient }
    }
}