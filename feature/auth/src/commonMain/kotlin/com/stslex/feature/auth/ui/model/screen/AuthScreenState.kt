package com.stslex.feature.auth.ui.model.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.stslex.feature.auth.ui.model.screen.text_field.LoginTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.PasswordInputTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.PasswordSubmitTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.UsernameTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.rememberLoginTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.rememberPasswordInputTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.rememberPasswordSubmitTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.rememberUsernameTextFieldState
import com.stslex.feature.auth.ui.store.AuthStoreComponent.Action
import com.stslex.feature.auth.ui.store.AuthStoreComponent.AuthFieldsState
import com.stslex.feature.auth.ui.store.AuthStoreComponent.ScreenLoadingState
import com.stslex.feature.auth.ui.store.AuthStoreComponent.State

@OptIn(ExperimentalComposeUiApi::class)
@Stable
data class AuthScreenState @OptIn(ExperimentalMaterialApi::class) constructor(
    val screenLoadingState: ScreenLoadingState = ScreenLoadingState.Content,
    val loginState: LoginTextFieldState,
    val usernameState: UsernameTextFieldState,
    val passwordEnterState: PasswordInputTextFieldState,
    val passwordSubmitState: PasswordSubmitTextFieldState,
    val authFieldsState: AuthFieldsState = AuthFieldsState.AUTH,
    val snackbarHostState: SnackbarHostState,
    val swipeableState: SwipeableState<AuthFieldsState>,
    private val processAction: (Action) -> Unit,
    private val keyboardController: SoftwareKeyboardController? = null
) {

    val isFieldsValid: Boolean
        get() {
            val isCorrectLength = loginState.text.length >= 6 &&
                    passwordEnterState.text.length >= 8
            val isEqualsPasswords = passwordEnterState.text == passwordSubmitState.text
            val isUsernameValid = usernameState.text.length >= 6
            val isRegisterPassword = authFieldsState == AuthFieldsState.AUTH
                    || (isEqualsPasswords && isUsernameValid)
            return isCorrectLength && isRegisterPassword
        }

    val isRegisterState = authFieldsState == AuthFieldsState.REGISTER

    fun onSubmitClicked(state: AuthFieldsState) {
        keyboardController?.hide()
        processAction(Action.OnSubmitClicked(state))
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun rememberAuthScreenState(
    screenState: State,
    snackbarHostState: SnackbarHostState,
    processAction: (Action) -> Unit,
): AuthScreenState {
    val keyboardController = LocalSoftwareKeyboardController.current
    val haptic = LocalHapticFeedback.current

    val usernameTextFieldState = rememberUsernameTextFieldState(
        text = screenState.username,
        processAction = processAction
    )

    val loginTextFieldState = rememberLoginTextFieldState(
        text = screenState.login,
        processAction = processAction
    )

    val passwordEnterState = rememberPasswordInputTextFieldState(
        processAction = processAction,
        text = screenState.password,
        authFieldsState = screenState.authFieldsState
    )

    val passwordSubmitState = rememberPasswordSubmitTextFieldState(
        processAction = processAction,
        text = screenState.passwordSubmit
    )

    val swipeableState = rememberSwipeableState(
        initialValue = AuthFieldsState.AUTH,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessHigh,
            visibilityThreshold = Spring.DefaultDisplacementThreshold
        )
    )

    LaunchedEffect(key1 = swipeableState.currentValue) {
        processAction(Action.OnAuthFieldChange(swipeableState.currentValue))
        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
    }

    return remember(screenState) {
        AuthScreenState(
            screenLoadingState = screenState.screenLoadingState,
            loginState = loginTextFieldState,
            passwordEnterState = passwordEnterState,
            passwordSubmitState = passwordSubmitState,
            authFieldsState = screenState.authFieldsState,
            snackbarHostState = snackbarHostState,
            processAction = processAction,
            swipeableState = swipeableState,
            keyboardController = keyboardController,
            usernameState = usernameTextFieldState
        )
    }
}
