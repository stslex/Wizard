package com.stslex.wizard.core.navigation.di

import androidx.navigation.NavHostController
import com.stslex.wizard.core.core.AppModule
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.core.navigation.navigator.NavigatorImpl
import org.koin.core.annotation.Module
import org.koin.dsl.ModuleDeclaration

@Module
class ModuleCoreNavigation(
    private val navHostController: NavHostController
) : AppModule() {

    override fun declaration(): ModuleDeclaration = {
        single<Navigator> { NavigatorImpl(navHostController) }
    }
}