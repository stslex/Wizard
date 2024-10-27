package com.stslex.wizard.core.database.store

interface UserStore {

    var accessToken: String
    var refreshToken: String
    var username: String
    var uuid: String

    fun clear()
}
