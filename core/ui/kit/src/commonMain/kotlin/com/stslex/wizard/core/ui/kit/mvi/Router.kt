package com.stslex.wizard.core.ui.kit.mvi

fun interface Router<in E : Store.Action.Navigation> {
    operator fun invoke(event: E)
}
