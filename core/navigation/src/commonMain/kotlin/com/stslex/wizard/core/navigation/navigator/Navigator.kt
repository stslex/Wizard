package com.stslex.wizard.core.navigation.navigator

import com.stslex.wizard.core.navigation.Screen

interface Navigator {

    fun navigateTo(
        screen: Screen,
        options: NavigatorOptions = NavigatorOptions()
    )

    fun popBack()
}