package com.stslex.core.database.di

import com.stslex.core.database.sources.source.FavouriteFilmDataSource
import com.stslex.core.database.sources.source.FavouriteFilmDataSourceImpl
import org.koin.dsl.module

val databaseModule = module {
    single<FavouriteFilmDataSource> { FavouriteFilmDataSourceImpl() }
}