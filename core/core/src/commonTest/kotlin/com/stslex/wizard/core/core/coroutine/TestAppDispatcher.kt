package com.stslex.wizard.core.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
object TestAppDispatcher : AppDispatcher {

    val testDispatcher: AppDispatcher = TestAppDispatcher

    override val io: CoroutineDispatcher = UnconfinedTestDispatcher()
    override val main: CoroutineDispatcher = UnconfinedTestDispatcher()
    override val default: CoroutineDispatcher = UnconfinedTestDispatcher()
    override val immediate: CoroutineDispatcher = UnconfinedTestDispatcher()
}