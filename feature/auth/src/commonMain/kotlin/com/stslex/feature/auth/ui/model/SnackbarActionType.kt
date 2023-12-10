package com.stslex.aproselection.feature.auth.ui.model

enum class SnackbarActionType(val action: String) {
    ERROR("error"),
    SUCCESS("success"),
    NONE("");

    companion object {

        fun getByAction(
            action: String?
        ): SnackbarActionType = SnackbarActionType.entries
            .firstOrNull { type ->
                type.action == action
            }
            ?: NONE
    }
}