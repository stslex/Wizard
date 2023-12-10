package com.stslex.core.database.store

import com.russhwolf.settings.set

class UserStoreImpl(
    private val userSettings: UserSettings
) : UserStore {

    override var userToken: String
        get() = userSettings.getString(KEY_TOKEN, EMPTY_VALUE)
        set(value) {
            userSettings[KEY_TOKEN] = value
        }

    override var refreshToken: String
        get() = userSettings.getString(KEY_REFRESH_TOKEN, EMPTY_VALUE)
        set(value) {
            userSettings[KEY_REFRESH_TOKEN] = value
        }

    override var username: String
        get() = userSettings.getString(KEY_USERNAME, EMPTY_VALUE)
        set(value) {
            userSettings[KEY_USERNAME] = value
        }

    override var uuid: String
        get() = userSettings.getString(KEY_UUID, EMPTY_VALUE)
        set(value) {
            userSettings[KEY_UUID] = value
        }

    override val isAuth: Boolean
        get() = userToken.isBlank().not()

    override fun clear() {
        userSettings.clear()
    }

    companion object {
        private const val KEY_TOKEN = "user.token"
        private const val KEY_REFRESH_TOKEN = "user.refresh_token"
        private const val KEY_USERNAME = "user.username"
        private const val KEY_UUID = "user.uuid"

        private const val EMPTY_VALUE = ""
    }
}