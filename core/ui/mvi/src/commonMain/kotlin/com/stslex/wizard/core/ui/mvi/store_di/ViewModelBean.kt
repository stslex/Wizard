package com.stslex.wizard.core.ui.mvi.store_di

import androidx.lifecycle.ViewModel
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

expect inline fun <reified R : ViewModel> Module.viewModelOf(
    crossinline constructor: () -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R>

expect inline fun <reified R : ViewModel, reified T1> Module.viewModelOf(
    crossinline constructor: (T1) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R>

expect inline fun <reified R : ViewModel, reified T1, reified T2> Module.viewModelOf(
    crossinline constructor: (T1, T2) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R>

expect inline fun <reified R : ViewModel, reified T1, reified T2, reified T3> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R>

expect inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R>

expect inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4, reified T5> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R>