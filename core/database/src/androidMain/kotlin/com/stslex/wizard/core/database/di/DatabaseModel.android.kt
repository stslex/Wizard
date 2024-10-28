package com.stslex.wizard.core.database.di

import android.content.Context
import com.russhwolf.settings.SharedPreferencesSettings
import com.stslex.wizard.core.database.store.UserSettings
import com.stslex.wizard.core.database.store.UserSettingsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

actual fun Module.declareUserSettingsStore() {
    single<UserSettings> {
        val delegate = androidContext()
            .getSharedPreferences(UserSettings.NAME, Context.MODE_PRIVATE)
        val prefs = SharedPreferencesSettings(delegate)
        UserSettingsImpl(prefs)
    }
}
