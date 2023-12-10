package com.stslex.core.database.di

import com.russhwolf.settings.PreferencesSettings
import com.stslex.core.database.store.UserSettings
import com.stslex.core.database.store.UserSettingsImpl
import org.koin.dsl.module
import java.util.prefs.Preferences

actual val userSettingsModule = module {
    val delegate = Preferences.userRoot().node(UserSettings.NAME)
    val prefsSettings = PreferencesSettings(delegate)
    single<UserSettings> { UserSettingsImpl(prefsSettings) }
}