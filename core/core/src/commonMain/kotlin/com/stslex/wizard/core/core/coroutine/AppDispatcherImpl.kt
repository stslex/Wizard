package com.stslex.wizard.core.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.MainCoroutineDispatcher

object AppDispatcherImpl : AppDispatcher {
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val main: MainCoroutineDispatcher = Dispatchers.Main
    override val immediate: MainCoroutineDispatcher = main.immediate
    override val default: CoroutineDispatcher = Dispatchers.Default
}