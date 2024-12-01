package com.stslex.wizard.feature.follower.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.kit.mvi.storeOf
import com.stslex.wizard.feature.follower.data.repository.FollowerRepository
import com.stslex.wizard.feature.follower.data.repository.FollowerRepositoryImpl
import com.stslex.wizard.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.wizard.feature.follower.domain.interactor.FollowerInteractorImpl
import com.stslex.wizard.feature.follower.navigation.FollowerRouter
import com.stslex.wizard.feature.follower.navigation.FollowerRouterImpl
import com.stslex.wizard.feature.follower.ui.store.FollowerStore
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureFollower : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::FollowerRepositoryImpl) { bind<FollowerRepository>() }
        factoryOf(::FollowerInteractorImpl) { bind<FollowerInteractor>() }
        factoryOf(::FollowerRouterImpl) { bind<FollowerRouter>() }
        storeOf(::FollowerStoreImpl) { bind<FollowerStore>() }
    }
}
