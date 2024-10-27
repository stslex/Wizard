package com.stslex.wizard.core.database.di

import com.stslex.wizard.core.database.sources.source.FavouriteFilmDataSource
import com.stslex.wizard.core.database.sources.source.FavouriteFilmDataSourceImpl
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.database.store.UserStoreImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val coreDatabaseModule = module {
    single<FavouriteFilmDataSource> { FavouriteFilmDataSourceImpl() }
    single<UserStore> { UserStoreImpl(userSettings = get()) }
}

expect val userSettingsModule: Module