package com.stslex.wizard

import com.stslex.wizard.di.appModules
import org.koin.core.context.startKoin

fun InitKoin() {
    startKoin {
        modules(appModules)
    }
}