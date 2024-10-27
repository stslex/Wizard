package com.stslex.wizard.feature.auth.ui.model.screen.text_field.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.stslex.wizard.feature.auth.ui.model.screen.text_field.hidden.HiddenState

@Stable
abstract class PasswordTextFieldState(
    private val hapticFeedback: HapticFeedback,
    private val hiddenState: HiddenState
) : AuthTextField() {

    override val label: String = "password"
    abstract val hint: String
    abstract val hasNext: Boolean

    val visualTransformation = derivedStateOf {
        if (hiddenState.isHidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    }

    val trailingIconRes = derivedStateOf {
        if (hiddenState.isHidden) {
            Icons.Outlined.Lock
//            R.drawable.baseline_visibility_off_24
        } else {
            Icons.Outlined.AccountBox

//            R.drawable.baseline_visibility_24
        }
    }

    fun onPasswordHideClicked() {
        hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        hiddenState.isHidden = hiddenState.isHidden.not()
    }
}

