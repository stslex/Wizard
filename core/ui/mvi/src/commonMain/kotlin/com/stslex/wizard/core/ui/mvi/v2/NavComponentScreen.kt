package com.stslex.wizard.core.ui.mvi.v2

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor.Companion.rememberStoreProcessor
import org.koin.compose.viewmodel.koinViewModel

@Suppress("UNCHECKED_CAST")
inline fun <
        reified TScreen : Screen,
        S : State,
        A : Action,
        E : Event,
        > NavGraphBuilder.navComponentScreenV2(
    builder: StoreBuilder<S, A, E>,
    noinline content: @Composable (TScreen, StoreProcessor<S, A, E>) -> Unit
) {
    navScreen<TScreen> { screen ->
        content(screen, builder())
    }
}

fun interface StoreBuilder<S : State, A : Action, E : Event> {

    @Composable
    operator fun invoke(): StoreProcessor<S, A, E>

    companion object {


        inline fun <reified TStoreImpl : BaseStore<S, A, E, *>, S : State, A : Action, E : Event> create(): StoreBuilder<S, A, E> =
            StoreBuilder {
                println("store = ${TStoreImpl::class.qualifiedName}")
                val store = koinViewModel<TStoreImpl>()
                println("store = $store")
                rememberStoreProcessor<TStoreImpl, S, A, E>()
            }
    }
}