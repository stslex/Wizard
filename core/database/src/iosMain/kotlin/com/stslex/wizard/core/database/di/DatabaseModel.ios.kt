package com.stslex.wizard.core.database.di

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.stslex.wizard.core.database.store.UserSettings
import com.stslex.wizard.core.database.store.UserSettingsImpl
import org.koin.core.module.Module
import platform.Foundation.NSUserDefaults

actual fun Module.declareUserSettingsStore() {
    val delegate = NSUserDefaults(suiteName = UserSettings.NAME)
    val settings: Settings = NSUserDefaultsSettings(delegate)
    single<UserSettings> { UserSettingsImpl(settings) }
}
