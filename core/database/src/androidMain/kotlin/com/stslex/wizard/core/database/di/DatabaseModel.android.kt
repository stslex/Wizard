package com.stslex.wizard.core.database.di

import com.russhwolf.settings.SharedPreferencesSettings
import com.stslex.wizard.core.database.store.UserSettings
import com.stslex.wizard.core.database.store.UserSettingsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

actual fun Module.declareUserSettingsStore() {
    single<UserSettings> {
        val delegate = createEncriptedSharedPreferences(
            context = androidContext(),
            name = UserSettings.NAME
        )
        val prefs = SharedPreferencesSettings(delegate)
        UserSettingsImpl(prefs)
    }
}
