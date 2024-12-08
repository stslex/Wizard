package com.stslex.wizard.feature.match_feed.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.ui.mvi.store_di.storeOf
import com.stslex.wizard.feature.match_feed.data.repository.MatchFeedMockRepositoryImpl
import com.stslex.wizard.feature.match_feed.data.repository.MatchFeedRepository
import com.stslex.wizard.feature.match_feed.domain.MatchFeedInteractor
import com.stslex.wizard.feature.match_feed.domain.MatchFeedInteractorImpl
import com.stslex.wizard.feature.match_feed.navigation.MatchFeedRouter
import com.stslex.wizard.feature.match_feed.navigation.MatchFeedRouterImpl
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreImpl
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleFeatureMatchFeed : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        factoryOf(::MatchFeedMockRepositoryImpl) { bind<MatchFeedRepository>() }
        factoryOf(::MatchFeedInteractorImpl) { bind<MatchFeedInteractor>() }
        factoryOf(::MatchFeedRouterImpl) { bind<MatchFeedRouter>() }
        storeOf(::MatchFeedStoreImpl) { bind<MatchFeedStore>() }
    }
}
