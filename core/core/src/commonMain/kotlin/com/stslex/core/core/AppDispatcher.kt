package com.stslex.core.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

interface AppDispatcher {
    val io: CoroutineDispatcher
    val main: MainCoroutineDispatcher
    val default: CoroutineDispatcher
}

class AppDispatcherImpl : AppDispatcher {
    override val io: CoroutineDispatcher = Dispatchers.Default // TODO IO
    override val main: MainCoroutineDispatcher = Dispatchers.Main
    override val default: CoroutineDispatcher = Dispatchers.Default
}