package com.stslex.wizard.feature.profile.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.kit.mvi.storeOf
import com.stslex.wizard.feature.profile.data.repository.ProfileRepository
import com.stslex.wizard.feature.profile.data.repository.ProfileRepositoryImpl
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractorImpl
import com.stslex.wizard.feature.profile.navigation.ProfileRouter
import com.stslex.wizard.feature.profile.navigation.ProfileRouterImpl
import com.stslex.wizard.feature.profile.ui.store.ProfileStore
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureProfile : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        storeOf(::ProfileStoreImpl) { bind<ProfileStore>() }
        factoryOf(::ProfileRouterImpl) { bind<ProfileRouter>() }
        factoryOf(::ProfileInteractorImpl) { bind<ProfileInteractor>() }
        factoryOf(::ProfileRepositoryImpl) { bind<ProfileRepository>() }
    }
}
