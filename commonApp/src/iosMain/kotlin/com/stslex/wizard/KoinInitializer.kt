package com.stslex.wizard

import com.stslex.wizard.di.appModules
import org.koin.core.context.startKoin

object KoinInitializer {

    fun init() {
        startKoin {
            modules(appModules)
        }
    }
}