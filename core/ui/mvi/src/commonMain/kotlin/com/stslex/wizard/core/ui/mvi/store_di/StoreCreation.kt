package com.stslex.wizard.core.ui.mvi.store_di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.Store
import org.koin.compose.koinInject
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@Composable
inline fun <reified T : Store<*, *, *>> getStore(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = koinInject(
    qualifier = qualifier,
    parameters = parameters
)
