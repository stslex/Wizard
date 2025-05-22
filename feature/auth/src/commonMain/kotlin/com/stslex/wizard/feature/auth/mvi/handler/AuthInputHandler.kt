package com.stslex.wizard.feature.auth.mvi.handler

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.auth.di.AuthScope
import com.stslex.wizard.feature.auth.mvi.AuthHandlerStore
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action.InputAction
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(AuthScope::class)
@Scoped
internal class AuthInputHandler : Handler<InputAction, AuthHandlerStore> {

    override fun AuthHandlerStore.invoke(action: InputAction) {
        when (action) {
            is InputAction.LoginInput -> processLoginInput(action)
            is InputAction.PasswordInput -> processPasswordInput(action)
            is InputAction.PasswordSubmitInput -> processPasswordSubmitInput(action)
            is InputAction.UsernameInput -> processUsernameInput(action)
        }
    }

    private fun AuthHandlerStore.processLoginInput(action: InputAction.LoginInput) {
        updateState { currentValue ->
            currentValue.copy(
                login = action.value
            )
        }
    }

    private fun AuthHandlerStore.processUsernameInput(action: InputAction.UsernameInput) {
        updateState { currentValue ->
            currentValue.copy(
                username = action.value
            )
        }
    }

    private fun AuthHandlerStore.processPasswordInput(action: InputAction.PasswordInput) {
        updateState { currentValue ->
            currentValue.copy(
                password = action.value
            )
        }
    }

    private fun AuthHandlerStore.processPasswordSubmitInput(action: InputAction.PasswordSubmitInput) {
        updateState { currentValue ->
            currentValue.copy(
                passwordSubmit = action.value,
            )
        }
    }
}