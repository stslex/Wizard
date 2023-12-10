package com.stslex.core.database.di

import android.content.Context
import com.russhwolf.settings.SharedPreferencesSettings
import com.stslex.core.database.store.UserSettings
import com.stslex.core.database.store.UserSettingsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val userSettingsModule = module {

    single<UserSettings> {
        val delegate = androidContext()
            .getSharedPreferences(UserSettings.NAME, Context.MODE_PRIVATE)
        val prefs = SharedPreferencesSettings(delegate)
        UserSettingsImpl(prefs)
    }
}
