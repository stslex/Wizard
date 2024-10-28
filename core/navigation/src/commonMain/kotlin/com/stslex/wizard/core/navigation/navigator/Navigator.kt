package com.stslex.wizard.core.navigation.navigator

import com.stslex.wizard.core.navigation.Screen

interface Navigator {

    fun navTo(
        screen: Screen,
        options: NavigatorOptions = NavigatorOptions()
    )

    fun popBack()
}