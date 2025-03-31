package com.stslex.wizard.core.ui.mvi.store_di

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.qualifier
import kotlin.reflect.KClass

inline fun <reified R : BaseStore<S, A, E, HStore>, S : Store.State, A : Store.Action, E : Store.Event, HStore : HandlerStore<S, A, E>, reified TScreen : Screen> Module.storeOf(
    screen: KClass<TScreen>,
    crossinline constructor: () -> R,
): KoinDefinition<R> = viewModelOf(constructor) {
    bind<BaseStore<S, A, E, HStore>>()
    qualifier = qualifier(screen.qualifiedName!!)
}

inline fun <reified R : BaseStore<S, A, E, HStore>, S : Store.State, A : Store.Action, E : Store.Event, HStore : HandlerStore<S, A, E>, reified TScreen : Screen, reified T1> Module.storeOf(
    screen: KClass<TScreen>,
    crossinline constructor: (T1) -> R,
): KoinDefinition<R> = viewModelOf(constructor) {
    bind<BaseStore<S, A, E, HStore>>()
    qualifier = qualifier(screen.qualifiedName!!)
}

inline fun <reified R : BaseStore<S, A, E, HStore>, S : Store.State, A : Store.Action, E : Store.Event, HStore : HandlerStore<S, A, E>, reified TScreen : Screen, reified T1, reified T2> Module.storeOf(
    screen: KClass<TScreen>,
    crossinline constructor: (T1, T2) -> R,
): KoinDefinition<R> = viewModelOf(constructor) {
    bind<BaseStore<S, A, E, HStore>>()
    qualifier = qualifier(screen.qualifiedName!!)
}


inline fun <reified R : BaseStore<S, A, E, HStore>, S : Store.State, A : Store.Action, E : Store.Event, HStore : HandlerStore<S, A, E>, reified TScreen : Screen, reified T1, reified T2, reified T3> Module.storeOf(
    screen: KClass<TScreen>,
    crossinline constructor: (T1, T2, T3) -> R,
): KoinDefinition<R> = viewModelOf(constructor) {
    bind<BaseStore<S, A, E, HStore>>()
    qualifier = qualifier(screen.qualifiedName!!)
}


inline fun <reified R : BaseStore<S, A, E, HStore>, S : Store.State, A : Store.Action, E : Store.Event, HStore : HandlerStore<S, A, E>, reified TScreen : Screen, reified T1, reified T2, reified T3, reified T4> Module.storeOf(
    screen: KClass<TScreen>,
    crossinline constructor: (T1, T2, T3, T4) -> R,
): KoinDefinition<R> = viewModelOf(constructor) {
    bind<BaseStore<S, A, E, HStore>>()
    qualifier = qualifier(screen.qualifiedName!!)
}


inline fun <reified R : BaseStore<S, A, E, HStore>, S : Store.State, A : Store.Action, E : Store.Event, HStore : HandlerStore<S, A, E>, reified TScreen : Screen, reified T1, reified T2, reified T3, reified T4, reified T5> Module.storeOf(
    screen: KClass<TScreen>,
    crossinline constructor: (T1, T2, T3, T4, T5) -> R,
): KoinDefinition<R> = viewModelOf(constructor) {
    bind<BaseStore<S, A, E, HStore>>()
    qualifier = qualifier(screen.qualifiedName!!)
}
