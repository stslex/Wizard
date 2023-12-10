package com.stslex.feature.auth.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.stslex.feature.auth.ui.model.screen.text_field.PasswordInputTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.PasswordSubmitTextFieldState
import com.stslex.feature.auth.ui.model.screen.text_field.base.PasswordTextFieldState

@Composable
internal fun AuthPasswordTextField(
    state: PasswordTextFieldState,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = state.text,
        onValueChange = state::onTextChange,
        singleLine = true,
        supportingText = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = state.hint
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = state.supportingEndText
                )
            }
        },
        visualTransformation = state.visualTransformation.value,
        label = {
            Text(text = state.label)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = when (state) {
                is PasswordSubmitTextFieldState -> ImeAction.Done
                is PasswordInputTextFieldState -> if (state.hasNext) ImeAction.Next else ImeAction.Done
                else -> ImeAction.None
            },
        ),
        trailingIcon = {
            IconButton(
                onClick = state::onPasswordHideClicked
            ) {
                Icon(
                    imageVector = state.trailingIconRes.value,
                    contentDescription = null
                )
            }
        },
    )
}
