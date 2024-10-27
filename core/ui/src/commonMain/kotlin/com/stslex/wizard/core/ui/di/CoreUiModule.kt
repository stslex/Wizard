package com.stslex.wizard.core.ui.di

import com.stslex.core.ui.pager.pager.StorePagerFactory
import com.stslex.core.ui.pager.pager.StorePagerFactoryImpl
import com.stslex.core.ui.pager.paging_worker.PagingWorkerFactory
import com.stslex.core.ui.pager.paging_worker.PagingWorkerFactoryImpl
import org.koin.dsl.module

val coreUiModule = module {
    factory<PagingWorkerFactory> {
        PagingWorkerFactoryImpl()
    }
    factory<StorePagerFactory> {
        StorePagerFactoryImpl(
            workerFactory = get()
        )
    }
}