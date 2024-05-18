package com.stslex.core.ui.mvi

import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

actual inline fun <reified S : Store<*, *, *>, reified T : BaseStore<*, *, *, *>> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<S> = factory<T>(
    qualifier = qualifier,
    definition = definition
) as KoinDefinition<S>