package com.stslex.wizard.feature.profile.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.store_di.storeOf
import com.stslex.wizard.feature.profile.data.repository.ProfileRepository
import com.stslex.wizard.feature.profile.data.repository.ProfileRepositoryImpl
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractorImpl
import com.stslex.wizard.feature.profile.mvi.ClickersHandler
import com.stslex.wizard.feature.profile.mvi.InitStorageHandler
import com.stslex.wizard.feature.profile.mvi.LogoutHandler
import com.stslex.wizard.feature.profile.mvi.NavigationHandler
import com.stslex.wizard.feature.profile.mvi.RepeatLastActionHandler
import com.stslex.wizard.feature.profile.ui.store.ProfileStore
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureProfile : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::InitStorageHandler)
        factoryOf(::LogoutHandler)
        factoryOf(::RepeatLastActionHandler)
        factoryOf(::ClickersHandler)
        factoryOf(::NavigationHandler)

        storeOf(::ProfileStoreImpl) { bind<ProfileStore>() }

        factoryOf(::ProfileInteractorImpl) { bind<ProfileInteractor>() }
        factoryOf(::ProfileRepositoryImpl) { bind<ProfileRepository>() }
    }
}
