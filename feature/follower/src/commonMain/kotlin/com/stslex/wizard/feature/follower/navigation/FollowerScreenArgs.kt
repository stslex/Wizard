package com.stslex.wizard.feature.follower.navigation

sealed class FollowerScreenArgs(
    open val uuid: String
) {

    data class Follower(
        override val uuid: String
    ) : FollowerScreenArgs(uuid)

    data class Following(
        override val uuid: String
    ) : FollowerScreenArgs(uuid)
}