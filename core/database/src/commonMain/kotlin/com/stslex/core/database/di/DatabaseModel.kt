package com.stslex.core.database.di

import com.stslex.core.database.sources.source.FavouriteFilmDataSource
import com.stslex.core.database.sources.source.FavouriteFilmDataSourceImpl
import com.stslex.core.database.store.UserStore
import com.stslex.core.database.store.UserStoreImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val coreDatabaseModule = module {
    single<FavouriteFilmDataSource> { FavouriteFilmDataSourceImpl() }
    single<UserStore> { UserStoreImpl(userSettings = get()) }
}

expect val userSettingsModule: Module