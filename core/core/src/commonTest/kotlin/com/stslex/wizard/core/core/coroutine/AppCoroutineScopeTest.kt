package com.stslex.wizard.core.core.coroutine

import com.stslex.wizard.core.core.coroutine.TestAppDispatcher.testDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AppCoroutineScopeTest {

    private val appScope = AppCoroutineScope(
        scope = TestScope(testDispatcher.default),
        appDispatcher = testDispatcher
    )

    @Test
    fun `test launch success with default params`() = runTest(testDispatcher.default) {
        val expectedResult = "Hello"
        var actualResult = "Nothing"
        val job = appScope.launch(
            onSuccess = { actualResult = it },
            onError = { throw it }
        ) {
            delay(100)
            expectedResult
        }
        assertTrue(job.isActive)
        job.join()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test launch error with default params`() = runTest(testDispatcher.default) {
        val expectedError = IllegalStateException("Error")
        var actualResult: Any? = null
        val job = appScope.launch(
            onSuccess = { actualResult = it },
            onError = { actualResult = it }
        ) {
            delay(100)
            throw expectedError
        }
        assertTrue(job.isActive)
        job.join()
        assertEquals(expectedError, actualResult)
    }

    @Test
    fun `test launch error with default params and cancellation`() {
        runTest(testDispatcher.default) {
            val expectedError = IllegalStateException("Error")
            var actualResult: Any? = null
            val job = appScope.launch(
                onSuccess = { actualResult = it },
                onError = { actualResult = it },
            ) {
                delay(100)
                throw expectedError
            }
            assertTrue(job.isActive)
            job.cancel()
            assertEquals(null, actualResult)
        }
    }

}

