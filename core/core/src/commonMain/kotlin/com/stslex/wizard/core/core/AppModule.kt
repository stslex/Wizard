package com.stslex.wizard.core.core

import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module
import org.koin.core.annotation.Module as KoinModule

@KoinModule
abstract class AppModule(
    createdAtStart: Boolean = false
) {

    abstract fun declaration(): ModuleDeclaration

    val module: Module by lazy {
        module(
            createdAtStart = createdAtStart,
            moduleDeclaration = declaration()
        )
    }
}