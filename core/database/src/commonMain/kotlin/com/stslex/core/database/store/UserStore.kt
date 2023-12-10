package com.stslex.core.database.store

interface UserStore {

    var userToken: String
    var refreshToken: String
    var username: String
    var uuid: String
    val isAuth: Boolean
    fun clear()
}
