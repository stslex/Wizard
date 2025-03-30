package com.stslex.wizard.core.ui.mvi.v2

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.store_di.getStoreV2

inline fun <reified S : Screen, TStore : Store<*, *, *>, reified TStoreImpl : BaseStore<*, *, *, *>> NavGraphBuilder.navComponentScreen(
    noinline content: @Composable (S, TStore) -> Unit
) {
    navScreen<S> { screen ->
        val store = getStoreV2<TStore, TStoreImpl>()
        content(screen, store)
    }
}