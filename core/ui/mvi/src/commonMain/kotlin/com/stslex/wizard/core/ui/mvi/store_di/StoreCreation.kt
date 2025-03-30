package com.stslex.wizard.core.ui.mvi.store_di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.core.ui.mvi.v2.BaseStore as BaseStoreV2
import com.stslex.wizard.core.ui.mvi.Store
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@Composable
inline fun <reified T : Store<*, *, *>> getStore(
    qualifier: Qualifier? = null,
): T = koinInject(qualifier)


@Suppress("UNCHECKED_CAST")
@Composable
inline fun <R : Store<*, *, *>, reified T : BaseStore<*, *, *>> getStore(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): R = koinViewModel<T>(
    qualifier = qualifier,
    parameters = parameters
) as R

@Suppress("UNCHECKED_CAST")
@Composable
inline fun <R : Store<*, *, *>, reified T : BaseStoreV2<*, *, *, *>> getStoreV2(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): R = koinViewModel<T>(
    qualifier = qualifier,
    parameters = parameters
) as R
