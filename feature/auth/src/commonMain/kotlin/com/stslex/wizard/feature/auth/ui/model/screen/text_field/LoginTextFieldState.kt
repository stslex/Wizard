package com.stslex.wizard.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.stslex.wizard.feature.auth.ui.model.screen.text_field.base.AuthTextField
import com.stslex.wizard.feature.auth.ui.store.AuthStore.Action.InputAction

@Stable
data class LoginTextFieldState(
    private val processAction: (InputAction.LoginInput) -> Unit,
    override val text: String,
) : AuthTextField() {

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(InputAction.LoginInput(value))
        }

    override val label: String = "login"
}

@Composable
fun rememberLoginTextFieldState(
    text: String,
    processAction: (InputAction.LoginInput) -> Unit
): LoginTextFieldState = remember(text) {
    LoginTextFieldState(
        text = text,
        processAction = processAction
    )
}
