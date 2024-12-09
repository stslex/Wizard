package com.stslex.wizard.core.ui.mvi

fun interface Router<in E : Store.Action.Navigation> {

    operator fun invoke(event: E)
}
