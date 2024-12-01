package com.stslex.wizard.core.ui.kit.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.kit.pager.pager.StorePagerFactory
import com.stslex.wizard.core.ui.kit.pager.pager.StorePagerFactoryImpl
import com.stslex.wizard.core.ui.kit.pager.paging_worker.PagingWorkerFactory
import com.stslex.wizard.core.ui.kit.pager.paging_worker.PagingWorkerFactoryImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleCoreUi : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::PagingWorkerFactoryImpl) { bind<PagingWorkerFactory>() }
        factoryOf(::StorePagerFactoryImpl) { bind<StorePagerFactory>() }
    }
}
