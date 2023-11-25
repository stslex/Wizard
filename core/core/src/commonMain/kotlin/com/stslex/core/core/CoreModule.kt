package com.stslex.core.core

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {
    singleOf<AppDispatcher>(::AppDispatcherImpl)
}