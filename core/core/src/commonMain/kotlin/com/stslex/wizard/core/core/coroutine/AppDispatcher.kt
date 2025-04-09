package com.stslex.wizard.core.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val immediate: CoroutineDispatcher
}

