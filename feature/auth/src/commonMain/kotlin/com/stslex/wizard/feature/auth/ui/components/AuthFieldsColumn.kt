package com.stslex.wizard.feature.auth.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.feature.auth.ui.model.screen.text_field.LoginTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.UsernameTextFieldState

@Composable
internal fun AuthFieldsColumn(
    state: AuthScreenState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            horizontal = AppDimension.Padding.big
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthLoginTextField(state.loginState)
        AnimatedVisibility(
            visible = state.isRegisterState,
            enter = fadeIn(tween(300)) + expandVertically(tween(600)),
            exit = fadeOut(tween(300)) + shrinkVertically(tween(600))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(AppDimension.Padding.small))
                AuthUsernameTextField(state.usernameState)
            }
        }
        Spacer(Modifier.height(AppDimension.Padding.medium))
        AuthPasswordTextField(state.passwordEnterState)
        AnimatedVisibility(
            visible = state.isRegisterState,
            enter = fadeIn(tween(300)) + expandVertically(tween(600)),
            exit = fadeOut(tween(300)) + shrinkVertically(tween(600))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(AppDimension.Padding.small))
                AuthPasswordTextField(state.passwordSubmitState)
            }
        }
        Spacer(Modifier.height(AppDimension.Padding.big))
        AuthSubmitButton(
            isValid = state.isFieldsValid,
            authFieldsState = state.authFieldsState,
            onClick = state::onSubmitClicked,
        )
    }
}

@Composable
private fun AuthUsernameTextField(
    state: UsernameTextFieldState,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = state.text,
        onValueChange = state::onTextChange,
        singleLine = true,
        label = {
            Text(text = "username")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        supportingText = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = state.supportingEndText
                )
            }
        },
    )
}

@Composable
private fun AuthLoginTextField(
    state: LoginTextFieldState,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = state.text,
        onValueChange = state::onTextChange,
        singleLine = true,
        label = {
            Text(text = "login")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        supportingText = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = state.supportingEndText
                )
            }
        },
    )
}