package com.stslex.wizard.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.stslex.wizard.feature.auth.ui.model.screen.text_field.base.AuthTextField
import com.stslex.feature.auth.ui.store.AuthStoreComponent.Action.InputAction

@Stable
data class UsernameTextFieldState(
    private val processAction: (InputAction.UsernameInput) -> Unit,
    override val text: String,
) : AuthTextField() {

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(InputAction.UsernameInput(value))
        }

    override val label: String = "username"
}

@Composable
fun rememberUsernameTextFieldState(
    text: String,
    processAction: (InputAction.UsernameInput) -> Unit
): UsernameTextFieldState = remember(text) {
    UsernameTextFieldState(
        text = text,
        processAction = processAction
    )
}
