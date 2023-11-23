package com.stslex.core.ui.navigation

import com.stslex.core.ui.mvi.Store

fun interface Router<in E : Store.Navigation> {
    operator fun invoke(event: E)
}