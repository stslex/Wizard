package com.stslex.wizard.feature.auth.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.store_di.storeOf
import com.stslex.wizard.feature.auth.data.AuthRepository
import com.stslex.wizard.feature.auth.data.AuthRepositoryImpl
import com.stslex.wizard.feature.auth.domain.AuthInteractor
import com.stslex.wizard.feature.auth.domain.AuthInteractorImpl
import com.stslex.wizard.feature.auth.ui.store.AuthStore
import com.stslex.wizard.feature.auth.ui.store.AuthStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureAuth : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
        factoryOf(::AuthInteractorImpl) { bind<AuthInteractor>() }
        storeOf(::AuthStoreImpl) { bind<AuthStore>() }
    }
}
