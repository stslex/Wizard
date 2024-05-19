package com.stslex.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.platform.LocalHapticFeedback
import com.stslex.feature.auth.ui.model.screen.text_field.base.PasswordTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.hidden.HiddenState
import com.stslex.feature.auth.ui.store.AuthStoreComponent.Action.InputAction.PasswordSubmitInput

@Stable
data class PasswordSubmitTextFieldState(
    private val hapticFeedback: HapticFeedback,
    private val processAction: (PasswordSubmitInput) -> Unit,
    private val hiddenState: HiddenState,
    override val text: String,
) : PasswordTextFieldState(
    hapticFeedback = hapticFeedback,
    hiddenState = hiddenState
) {

    override val hint: String = "repeat password"
    override val hasNext: Boolean = true
    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(PasswordSubmitInput(value))
        }
}

@Composable
fun rememberPasswordSubmitTextFieldState(
    processAction: (PasswordSubmitInput) -> Unit,
    text: String,
): PasswordSubmitTextFieldState {
    val hapticFeedback = LocalHapticFeedback.current
    val hiddenState = remember {
        HiddenState()
    }
    return remember(text) {
        PasswordSubmitTextFieldState(
            hapticFeedback = hapticFeedback,
            processAction = processAction,
            hiddenState = hiddenState,
            text = text
        )
    }
}