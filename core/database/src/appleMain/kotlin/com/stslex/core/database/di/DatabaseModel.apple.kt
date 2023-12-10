package com.stslex.core.database.di

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.stslex.core.database.store.UserSettings
import com.stslex.core.database.store.UserSettingsImpl
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual val userSettingsModule = module {
    val delegate = NSUserDefaults(suiteName = UserSettings.NAME)
    val settings: Settings = NSUserDefaultsSettings(delegate)
    single<UserSettings> { UserSettingsImpl(settings) }
}