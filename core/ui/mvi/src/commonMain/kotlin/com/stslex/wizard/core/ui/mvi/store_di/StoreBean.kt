package com.stslex.wizard.core.ui.mvi.store_di

import com.stslex.wizard.core.ui.mvi.BaseStore
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf

inline fun <reified R : BaseStore<*, *, *>> Module.storeOf(
    crossinline constructor: () -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R> = viewModelOf(constructor, options)

inline fun <reified R : BaseStore<*, *, *>, reified T1> Module.storeOf(
    crossinline constructor: (T1) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R> = viewModelOf(constructor, options)

inline fun <reified R : BaseStore<*, *, *>, reified T1, reified T2> Module.storeOf(
    crossinline constructor: (T1, T2) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R> = viewModelOf(constructor, options)

inline fun <reified R : BaseStore<*, *, *>, reified T1, reified T2, reified T3> Module.storeOf(
    crossinline constructor: (T1, T2, T3) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R> = viewModelOf(constructor, options)

inline fun <reified R : BaseStore<*, *, *>, reified T1, reified T2, reified T3, reified T4> Module.storeOf(
    crossinline constructor: (T1, T2, T3, T4) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R> = viewModelOf(constructor, options)

inline fun <reified R : BaseStore<*, *, *>, reified T1, reified T2, reified T3, reified T4, reified T5> Module.storeOf(
    crossinline constructor: (T1, T2, T3, T4, T5) -> R,
    noinline options: (BeanDefinition<R>.() -> Unit)? = null,
): KoinDefinition<R> = viewModelOf(constructor, options)