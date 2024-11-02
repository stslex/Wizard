package com.stslex.wizard.core.core

import org.koin.core.annotation.Module
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleCore : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        single<AppDispatcher> { AppDispatcherImpl }
    }
}