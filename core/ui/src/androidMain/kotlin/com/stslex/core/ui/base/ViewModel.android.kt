package com.stslex.core.ui.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import com.stslex.core.ui.base.ViewModel as VM

actual open class ViewModel : ViewModel() {

    actual val scope: CoroutineScope
        get() = viewModelScope
}

actual inline fun <reified T : VM> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = viewModel(qualifier = qualifier, definition = definition)

@Composable
actual inline fun <reified T : VM> getViewModel(): T = koinViewModel()