package com.stslex.wizard.core.ui.mvi.v2

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.store_di.getStoreV2
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor.Companion.rememberStoreProcessor
import org.koin.core.qualifier.named

inline fun <reified S : Screen, TStore : Store<*, *, *>, reified TStoreImpl : BaseStore<*, *, *, *>> NavGraphBuilder.navComponentScreen(
    noinline content: @Composable (S, TStore) -> Unit
) {
    navScreen<S> { screen ->

        val store = getStoreV2<TStore, TStoreImpl>()
        content(screen, store)
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <
        reified TScreen : Screen,
        S : Store.State,
        A : Store.Action,
        E : Store.Event,
        > NavGraphBuilder.navComponentScreenV2(
    noinline content: @Composable (TScreen, StoreProcessor<S, A, E>) -> Unit
) {
    navScreen<TScreen> { screen ->
        val processor = rememberStoreProcessor<S, A, E, Store<S, A, E>>(
            qualifier = named(TScreen::class.qualifiedName!!)
        )
        content(screen, processor)
    }
}