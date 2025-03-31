package com.stslex.wizard.core.ui.mvi.v2.processor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.viewmodel.koinViewModel

@Immutable
interface StoreProcessor<S : Store.State, A : Store.Action, E : Store.Event> {

    val state: State<S>

    fun consume(action: A)

    @Composable
    fun handle(block: suspend CoroutineScope.(E) -> Unit)

    companion object {

        @Composable
        inline fun <reified TStoreImpl : BaseStore<S, A, E, *>, S : Store.State, A : Store.Action, E : Store.Event> rememberStoreProcessor(): StoreProcessor<S, A, E> {
            val store = koinViewModel<TStoreImpl>()
            val actionProcessor = remember { ActionProcessor(store) }
            val effectsProcessor = remember { EffectsProcessor(store) }
            val state = remember { store.state }.collectAsState()
            return remember {
                StoreProcessorImpl<S, A, E, TStoreImpl>(
                    actionProcessor = actionProcessor,
                    eventProcessor = effectsProcessor,
                    state = state,
                )
            }
        }
    }
}

