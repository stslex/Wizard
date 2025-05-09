package com.stslex.wizard.feature.settings.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.store_di.storeOf
import com.stslex.wizard.feature.settings.domain.SettingsInteractor
import com.stslex.wizard.feature.settings.domain.SettingsInteractorImpl
import com.stslex.wizard.feature.settings.ui.store.SettingsStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureSettings : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::SettingsInteractorImpl) { bind<SettingsInteractor>() }
        storeOf(::SettingsStoreImpl)
    }
}