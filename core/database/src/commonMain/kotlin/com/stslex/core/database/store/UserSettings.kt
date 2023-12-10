package com.stslex.core.database.store

import com.russhwolf.settings.Settings

interface UserSettings : Settings {

    companion object {
        const val NAME = "user.settings"
    }
}

class UserSettingsImpl(delegate: Settings) : UserSettings, Settings by delegate