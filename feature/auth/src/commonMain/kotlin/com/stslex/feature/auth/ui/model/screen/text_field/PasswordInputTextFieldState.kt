package com.stslex.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.platform.LocalHapticFeedback
import com.stslex.feature.auth.ui.model.screen.text_field.hidden.HiddenState
import com.stslex.feature.auth.ui.model.screen.text_field.base.PasswordTextFieldState
import com.stslex.feature.auth.ui.store.AuthStore.Action.InputAction
import com.stslex.feature.auth.ui.store.AuthStore.AuthFieldsState

@Stable
data class PasswordInputTextFieldState(
    private val hapticFeedback: HapticFeedback,
    private val processAction: (InputAction.PasswordInput) -> Unit,
    private val hiddenState: HiddenState,
    override val text: String,
    val authFieldsState: AuthFieldsState
) : PasswordTextFieldState(
    hapticFeedback = hapticFeedback,
    hiddenState = hiddenState
) {

    override val hint: String = "enter password"
    override val hasNext: Boolean = authFieldsState == AuthFieldsState.REGISTER

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(InputAction.PasswordInput(value))
        }
}

@Composable
fun rememberPasswordInputTextFieldState(
    processAction: (InputAction.PasswordInput) -> Unit,
    text: String,
    authFieldsState: AuthFieldsState
): PasswordInputTextFieldState {
    val hapticFeedback = LocalHapticFeedback.current
    val hiddenState = remember { HiddenState() }
    return remember(text, authFieldsState) {
        PasswordInputTextFieldState(
            hapticFeedback = hapticFeedback,
            processAction = processAction,
            hiddenState = hiddenState,
            text = text,
            authFieldsState = authFieldsState
        )
    }
}