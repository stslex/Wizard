package com.stslex.feature.follower.ui

sealed interface FollowerScreenArgs {

    data class Follower(
        val uuid: String
    ) : FollowerScreenArgs

    data class Following(
        val uuid: String
    ) : FollowerScreenArgs
}