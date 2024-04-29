package com.stslex.core.ui.di

import com.stslex.core.ui.pager.pager.StorePagerFactory
import com.stslex.core.ui.pager.pager.StorePagerFactoryImpl
import org.koin.dsl.module

val coreUiModule = module {
    factory<StorePagerFactory> {
        StorePagerFactoryImpl()
    }
}