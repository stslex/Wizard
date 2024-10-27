package com.stslex.wizard.feature.auth.ui.model.screen.text_field.base

import androidx.compose.runtime.Stable

@Stable
abstract class AuthTextField {

    abstract val text: String
    abstract val label: String
    abstract val sendAction: (text: String) -> Unit

    val supportingEndText: String
        get() = "${text.length}/$MAX_SYMBOL_COUNT"

    fun onTextChange(value: String) {
        val currentValue = value.trim()
        if (text == currentValue || currentValue.length > MAX_SYMBOL_COUNT) return
        sendAction(currentValue)
    }

    companion object {
        private const val MAX_SYMBOL_COUNT = 16
    }
}
