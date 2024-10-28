package com.stslex.wizard.core.database.di

import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.database.sources.source.FavouriteFilmDataSource
import com.stslex.wizard.core.database.sources.source.FavouriteFilmDataSourceImpl
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.database.store.UserStoreImpl
import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import org.koin.core.annotation.Module as KoinModule

@KoinModule
class ModuleCoreDatabase : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        declareUserSettingsStore()
        single<FavouriteFilmDataSource> { FavouriteFilmDataSourceImpl() }
        single<UserStore> { UserStoreImpl(userSettings = get()) }
    }
}

expect fun Module.declareUserSettingsStore()
